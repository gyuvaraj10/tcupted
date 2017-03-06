package com.tests.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.tcup.ted"})
public class BootStrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootStrapApplication.class, args);
    }

//    public static void main(String[] args) throws Exception{
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(TcupTedScanner.class);
//        context.refresh();
//        TestClassHelper service = context.getBean(TestClassHelper.class);
//        Future<Object> objectFuture = service.gitHubService.createProject("testProject");
//        ElementField elementField = ElementField.Builder.elementField()
//                .withIdentifier("name")
//                .withName("username")
//                .withValue("user")
//                .build();
//        ElementField elementField1 = ElementField.Builder.elementField()
//            .withIdentifier("css")
//            .withName("username1")
//            .withValue("user1")
//            .build();
//        List<ElementField> fields = new ArrayList<>();
//        fields.add(elementField);
//        fields.add(elementField1);
//        IPageObjectGenerator generator = service.provider.getObject();
//        System.out.println(generator.generatePageObject("LoginPage", fields));
////        String content = service.pageObjectGenerator.generatePageObject("LogonPage", fields);
////        FileUtils.writeStringToFile(new File("/Users/Yuvaraj/dev/mytoold/tcupted/src/main/resources/LoginPage.java"),content, "UTF-8" );
//
////        System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "gitignoreTemplate"));
////        System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "autoInit"));
////        System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "Private"));
////        System.out.println(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "name"));
//    }
}
