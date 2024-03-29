package com.durys.jakub.recruitmentapp.architecture;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureTests {

    @Test
    void domainLayerShouldNotDependOnInfrastructure() {

        JavaClasses classes = new ClassFileImporter()
                .importPackages("com.durys.jakub.recruitmentapp");

        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("..domain..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..infrastructure..");

        rule.check(classes);
    }

    @Test
    void applicationLayerShouldNotDependOnInfrastructure() {

        JavaClasses classes = new ClassFileImporter()
                .importPackages("com.durys.jakub.recruitmentapp");

        ArchRule rule = noClasses()
                .that()
                .resideInAPackage("..application..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..infrastructure..");

        rule.check(classes);
    }

}
