//package com.software.rateit;
//
//import com.software.rateit.Entity.User;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import javax.persistence.EntityManager;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//public class RaportGenerator{
//
//    private void addTextElement (Document doc, Element parent, String name, String text){
//        Element e = doc.createElement(name);
//        e.appendChild(doc.createTextNode(text));
//        parent.appendChild(e);
//    }
//
//    private void saveUser (User u, Document doc, Element parent, int numberOfActivities){
//        Element userU = doc.createElement("User");
//        parent.appendChild(userU);
//        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
//        userU.setAttribute("id", String.valueOf(u.getId()));
//        addTextElement(doc, userU, "registration_date", d.format(u.getRegistrationDate()));
//        addTextElement(doc, userU, "email", u.getEmail());
//        addTextElement(doc, userU, "nick", u.getNick());
//    }
//
//    public void sumOfRegistration (EntityManager entityManager, Date registrationDate, String path) throws Exception {
//
//        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
//        String rd = d.format(registrationDate);
//        System.out.println(rd);
//        /*Query query = entityManager.createQuery("SELECT u FROM User u WHERE date(registrationDate) = '" + rd + "'");
//        @SuppressWarnings("unchecked")
//        List<User> user = query.getResultList();
//
//
//        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//        Document doc = docBuilder.newDocument();
//
//        Element userU = doc.createElement("User");
//        doc.appendChild(userU);
//        userU.setAttribute("registrationDate", d.format(registrationDate));
//
//        for (User u : user) {
//            System.out.println("id_user: " + u.getId());
//            saveUser(u, doc, userU, user.size());
//        }
//
//
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        DOMSource source = new DOMSource(doc);
//        StreamResult result = new StreamResult(new File(path));
//        transformer.transform(source, result);*/
//    }
//    public void sumUsersRegistrationOfYesterday (EntityManager entityManager, String catalog) throws Exception {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
//        Date data = calendar.getTime();
//        String fileName = catalog + new SimpleDateFormat("yyyy-MM-dd").format(data) + ".xml";
//        System.out.println(fileName);
//        sumOfRegistration(entityManager, data, fileName);
//    }
//}