package org.autotesting;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class Program {
    public static void main(String[] args) {
        var fileDirectory = String.format("%s/Database/src/main/java", System.getProperty("user.dir"));
        try {
            GenerationTool.generate(new Configuration()
                    .withJdbc(new Jdbc()
                            .withUrl("jdbc:postgresql://localhost:5432/trader_db")
                            .withPassword("secret123!")
                            .withUsername("postgres")
                            .withDriver("org.postgresql.Driver"))
                    .withGenerator(new Generator()
                            .withTarget(new Target()
                                    .withDirectory(fileDirectory)
                                    .withPackageName("org.autotesting.database"))
                            .withDatabase(new Database()
                                    .withIncludes(".*")
                                    .withName("org.jooq.meta.postgres.PostgresDatabase")
                                    .withInputSchema("public"))
                            .withGenerate(new Generate()
                                    .withDaos(false)
                                    .withPojos(false))));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
