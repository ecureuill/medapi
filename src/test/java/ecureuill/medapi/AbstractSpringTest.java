package ecureuill.medapi;

import org.springframework.test.context.ActiveProfiles;

// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = MedapiApplication.class)
@ActiveProfiles("test")
// @WebAppConfiguration
/**
 * If you have any property file to load to test uncomment below line) 
   @TestPropertySource({
   "classpath:/properties/dbConfig-test.properties",
   "classpath:/properties/unittest.properties"
   })
*/
public abstract class AbstractSpringTest{}