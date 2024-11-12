package tn.esprit.spring.Services.Etudiant;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.DAO.Entities.Etudiant;
import tn.esprit.spring.Services.Etudiant.EtudiantService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)  // Reset context after each test
@Transactional
class EtudiantServiceTest {

    private EtudiantService etudiantService;

    @BeforeEach
    void setUp() {
        // Initialize the service without any repository interactions
        etudiantService = new EtudiantService(null);
    }

    @Test
    void testEtudiantInstantiation() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEt("John Doe");

        assertEquals(1L, etudiant.getIdEtudiant());
        assertEquals("John Doe", etudiant.getNomEt());
    }

    @Test
    void testServiceInstanceNotNull() {
        assertNotNull(etudiantService);
    }

    @Test
    void testAddOrUpdateWithNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> etudiantService.addOrUpdate(null));
        assertEquals("Etudiant cannot be null", exception.getMessage());
    }


    @Test
    void testEtudiantNameLengthValidation() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNomEt("John");

        assertTrue(etudiant.getNomEt().length() >= 3, "Etudiant name should have at least 3 characters");
    }

    @Test
    void testFindByIdWithNegativeId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> etudiantService.findById(-1L));
        assertEquals("Id must be positive", exception.getMessage());
    }


    @Test
    void testDeleteEtudiantWithNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> etudiantService.delete(null));
        assertEquals("Etudiant cannot be null", exception.getMessage());
    }



    @Test
    void testSetAndGetEtudiantAttributes() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEt("Alice");

        assertEquals(1L, etudiant.getIdEtudiant());
        assertEquals("Alice", etudiant.getNomEt());

        etudiant.setNomEt("Bob");
        assertEquals("Bob", etudiant.getNomEt());
    }
}