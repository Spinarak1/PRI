//package com.software.rateit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class RaportGeneratorScheduler {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private static final Logger log = LoggerFactory.getLogger(RaportGeneratorScheduler.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    @Scheduled(cron = "0 * * * * *")
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//        try {
//            RaportGenerator generator = new RaportGenerator();
//            generator.sumUsersRegistrationOfYesterday(entityManager, "C:\\Users\\Spinarak\\Desktop\\XML\\");
//        }catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//        } finally {
//        }
//    }}