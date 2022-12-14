package org.polytech.covid.Config;

import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CustomETagFilter extends ShallowEtagHeaderFilter {

    private static final String DIRECTIVE_NO_STORE = "no-store";
    private static Set<String> cache = new HashSet<>();
    private static final List<String> modifyMethod = Arrays.asList(
            HttpMethod.POST.toString(),
            HttpMethod.PUT.toString(),
            HttpMethod.PATCH.toString());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (HttpMethod.GET.toString().contains(request.getMethod()) && notModified(request)) {
            response.sendError(HttpStatus.NOT_MODIFIED.value());
            return;
        }

        if (modifyMethod.contains(request.getMethod()) && preconditionFailed(request)) {
            response.sendError(HttpStatus.PRECONDITION_FAILED.value());
            return;
        }

        super.doFilterInternal(request, response, filterChain);

        updateCache(request, response);
    }

    private boolean preconditionFailed(HttpServletRequest request) {
        String ifMatchEtag = request.getHeader(HttpHeaders.IF_MATCH);
        if (Strings.isNotBlank(ifMatchEtag)) {
            return !cache.contains(ifMatchEtag);
        }
        return false;
    }

    private boolean notModified(HttpServletRequest request) {
        String ifNoneMatchEtag = request.getHeader(HttpHeaders.IF_NONE_MATCH);
        if (Strings.isNotBlank(ifNoneMatchEtag)) {
            return !cache.contains(ifNoneMatchEtag);
        }
        return false;
    }

    private void updateCache(HttpServletRequest request, HttpServletResponse response) {
        String ifMatchEtag = request.getHeader(HttpHeaders.IF_MATCH);
        if (Strings.isNotBlank(ifMatchEtag)) {
            cache.remove(ifMatchEtag);
        }

        String etag = response.getHeader(HttpHeaders.ETAG);
        if (Strings.isNotBlank(etag)) {
            cache.add(etag);
            System.out.println("Etag ajout?? au cache");
        }
    }

    @Override
    protected boolean isEligibleForEtag(HttpServletRequest request, HttpServletResponse response,
            int responseStatusCode, InputStream inputStream) {

        if (!response.isCommitted() &&
                responseStatusCode >= 200 && responseStatusCode < 300) {

            String cacheControl = response.getHeader(HttpHeaders.CACHE_CONTROL);
            return (cacheControl == null || !cacheControl.contains(DIRECTIVE_NO_STORE));
        }

        return false;
    }
}