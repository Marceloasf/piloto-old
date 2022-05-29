package br.com.piloto.test;

import br.com.piloto.resource.user.UserResource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Rollback
@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = UserResource.class)
//@ActiveProfiles(value = "test")
public @interface ResourceTest {

    @AliasFor(value = "controllers", annotation = WebMvcTest.class)
    Class<?>[] value() default {};
}
