package com.parma.dal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import com.parma.configuration.SpringMongoConfiguration;
import com.parma.model.Calibration;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CalibrationDal {

  private static Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  
  public static Calibration loadCalibration(String title) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfiguration.class);
    final MongoOperations mongoOps = (MongoOperations) ctx.getBean("mongoTemplate");
    // get a single calibration linked to the user  
    Query query = new Query(Criteria.where("title").is(title).and("owner").is(auth.getName()));
    return mongoOps.findOne(query, Calibration.class);    
  }

  public static List<Calibration> loadAllCalibrations() {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfiguration.class);
    final MongoOperations mongoOps = (MongoOperations) ctx.getBean("mongoTemplate");
    // generate array of calibrations linked to the user
    List<Calibration> cals = new ArrayList<Calibration>();
    Query query = new Query(Criteria.where("owner").is(auth.getName()));
    cals = mongoOps.find(query, Calibration.class);
    return cals;
  }

  public static void saveCalibration(Calibration cal) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfiguration.class);
    final MongoOperations mongoOps = (MongoOperations) ctx.getBean("mongoTemplate");
    // save the calibration in mongodb
    mongoOps.save(cal, "Calibration");
  }

  public static void removeCalibration(String title) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfiguration.class);
    final MongoOperations mongoOps = (MongoOperations) ctx.getBean("mongoTemplate");
    // search calibration by title
    Query query = new Query(Criteria.where("title").is(title).and("owner").is(auth.getName()));
    // remove the found calibration
    mongoOps.remove(query, Calibration.class);
  }

} // end DAL
