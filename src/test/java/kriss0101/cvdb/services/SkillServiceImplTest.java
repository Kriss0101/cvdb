package kriss0101.cvdb.services;

import kriss0101.cvdb.datamodel.Skill;
import kriss0101.cvdb.repositories.SkillRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class SkillServiceImplTest {

    @Mock
    SkillRepository repo;

    SkillService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new SkillServiceImpl(repo);


    }

    @Test
    public void testGetById() {
        Skill s = new Skill();
        s.setName("Java");
        s.setId(1L);
        when(repo.findOne(anyLong())).thenReturn(s);

        Optional<Skill> sFound = service.getSkillById(1L);

        assertThat(sFound.get()).isEqualTo(s);

    }

    @Test
    public void testGetSkills() {
        // Given
        Skill s = new Skill();
        s.setName("Java");
        s.setId(1L);

        Skill s2 = new Skill();
        s2.setName(".NET");
        s2.setId(2L);

        Skill s3 = new Skill();
        s.setName("C");
        s.setId(3L);
        when(repo.findAll()).thenReturn(Arrays.asList(s,s2,s3));

        // when
        List<Skill> sFound = service.getSkills();

        // then
        assertThat(sFound.size()).isEqualTo(3);
    }
}