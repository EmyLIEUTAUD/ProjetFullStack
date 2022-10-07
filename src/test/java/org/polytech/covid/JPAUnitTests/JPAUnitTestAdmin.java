package org.polytech.covid.JPAUnitTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JPAUnitTestAdmin {

    @Mock
    private TestEntityManager entityManager;

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    public void should_find_no_persons_if_repository_is_empty() {
        Iterable personnes = personneRepository.findAll();

        assertThat(personnes).isEmpty();
    }

    @Test
    public void should_store_a_person() {
        Personne personne = personneRepository.save(new Personne("NomPersonne", "PrenomPersonne",
                "mailPersonne@mail.com", "+33111111111", "1, adresse de la personne"));

        assertThat(personne).hasFieldOrPropertyWithValue("nom", "NomPersonne");
        assertThat(personne).hasFieldOrPropertyWithValue("prenom", "PrenomPersonne");
        assertThat(personne).hasFieldOrPropertyWithValue("mail", "mailPersonne@mail.com");
        assertThat(personne).hasFieldOrPropertyWithValue("telephone", "+33111111111");
        assertThat(personne).hasFieldOrPropertyWithValue("adresse", "1, adresse de la personne");
    }

    @Test
    public void should_find_all_persons() {
        Personne personne1 = new Personne("NomPersonne1", "PrenomPersonne1",
                "mailPersonne1@mail.com", "+33111111111", "1, adresse de la personne 1");
        entityManager.persist(personne1);

        Personne personne2 = new Personne("NomPersonne2", "PrenomPersonne2",
                "mailPersonn2@mail.com", "+33222222222", "2, adresse de la personne 2");
        entityManager.persist(personne2);

        Personne personne3 = new Personne("NomPersonne3", "PrenomPersonne3",
                "mailPersonn3@mail.com", "+33333333333", "3, adresse de la personne 3");
        entityManager.persist(personne3);

        Iterable personnes = personneRepository.findAll();

        assertThat(personnes).hasSize(3).contains(personne1, personne2, personne3);
    }

}
