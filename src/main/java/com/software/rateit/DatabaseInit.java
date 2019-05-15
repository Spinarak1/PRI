/*package com.software.rateit;



import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Singleton
@Startup
public class DatabaseInit {


    @PersistenceContext(unitName = "primary")
    protected EntityManager em;

    @PostConstruct
    public void init(){


        Artist a1 = new Artist(1L, "Adam", new CD(), tr1);
        Artist a2 = new Artist(2L, "Adam", new CD(), tr3);
        Artist a3 = new Artist(3L, "Adam", new CD(), tr2);

        Track tr1 = new Track("ww", 2000-01-01, a1, cd1, g1);
        Track tr2 = new Track("ss", 2011-02-02, a3, cd2, g2);
        Track tr3 = new Track("xx", 2012-03-03, a2, cd3, g3);

        CD cd1 = new CD("haha", 2018-01-01, a1, tr1, g3);
        CD cd2 = new CD("hehe", 2018-21-01, a2, tr2, g2);
        CD cd3 = new CD("hihi", 2019-01-01, a3, tr3, g1);

        em.persist(cd1);
        em.persist(cd2);
        em.persist(cd3);

        em.persist(tr1);
        em.persist(tr2);
        em.persist(tr3);

        em.persist(a1);
        em.persist(a2);
        em.persist(a3);

    }
}*/