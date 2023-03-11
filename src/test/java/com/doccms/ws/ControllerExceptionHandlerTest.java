package com.doccms.ws;

import static org.assertj.core.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import com.doccms.adapter.ws.admin.exception.AdminControllerExceptionHandler;
import io.github.classgraph.ClassGraph;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

class ControllerExceptionHandlerTest {
    private static final String ADMIN_WS_PACKAGE = "com.doccms.adapter.ws.admin";

    private static List<String> findAllExceptionClassesInPackage(String packageName) {
        return new ClassGraph().enableAllInfo().acceptPackages(packageName).scan()
                               .getAllStandardClasses()
                               .filter(clazz -> clazz.extendsSuperclass("java.lang.RuntimeException"))
                               .loadClasses().stream()
                               .map(Class::getName)
                               .toList();
    }

    private static List<String> extractExceptionClassesFromExceptionHandlerAnnotations(Class clazz) {
        return Arrays.stream(ClassUtils.getUserClass(clazz).getMethods())
                     .filter(method -> AnnotationUtils.getAnnotation(method, ExceptionHandler.class) != null)
                     .map(method -> method.getAnnotation(ExceptionHandler.class).value())
                     .flatMap(Arrays::stream)
                     .map(Class::getName)
                     .toList();
    }

    @Test
    void adminControllerExceptionHandlerShouldHandleAllAdminControllerExceptions() {
        var internalControllerExceptions = findAllExceptionClassesInPackage(ADMIN_WS_PACKAGE);
        var internalControllerHandledExceptions = extractExceptionClassesFromExceptionHandlerAnnotations(
            AdminControllerExceptionHandler.class);
        assertAllExceptionsAreHandled(internalControllerExceptions, internalControllerHandledExceptions);
    }

    private void assertAllExceptionsAreHandled(List<String> exceptions, List<String> handledExceptions) {
        exceptions.stream()
                  .filter(ex -> !handledExceptions.contains(ex))
                  .forEach(ex -> fail("An unhandled exception was found : " + ex));
    }

}
