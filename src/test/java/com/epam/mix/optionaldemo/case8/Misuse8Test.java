package com.epam.mix.optionaldemo.case8;

import com.epam.mix.optionaldemo.case8.objects.ColumnDefinition;
import com.epam.mix.optionaldemo.case8.objects.TemplateReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ALL")
@RunWith(MockitoJUnitRunner.class)
public class Misuse8Test {

    private ColumnDefinition columnDefinition;
    private ColumnDefinition parentColumnDefinition;

    @Before
    public void setUp() {
        TemplateReference templateReference = new TemplateReference();
        templateReference.setIdentifier("regular");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("param", "regular-param");

        templateReference.setParameterValues(parameters);

        TemplateReference parentTemplateReference = new TemplateReference();
        parentTemplateReference.setIdentifier("regular");

        Map<String, String> parametersForParent = new HashMap<>();
        parametersForParent.put("param", "parent-param");

        parentTemplateReference.setParameterValues(parametersForParent);

        columnDefinition = new ColumnDefinition();
        columnDefinition.setTemplateReference(templateReference);

        parentColumnDefinition = new ColumnDefinition();
        parentColumnDefinition.setTemplateReference(parentTemplateReference);
    }

    private TemplateReference misuse() {
        return Optional.ofNullable(columnDefinition == null ? null : columnDefinition.getTemplateReference())
                .map(templateReference -> new TemplateReference(templateReference.getIdentifier(), new LinkedHashMap<>(templateReference.getParameterValues())))
                .orElseGet(() -> Optional.ofNullable(parentColumnDefinition.getTemplateReference())
                        .map(templateReference -> new TemplateReference(templateReference.getIdentifier(), new LinkedHashMap<>(templateReference.getParameterValues())))
                        .orElse(null));
    }

    private TemplateReference useProperly() {
        return Optional.ofNullable(columnDefinition)
                .map(ColumnDefinition::getTemplateReference)
                .map(TemplateReference::new)
                .orElseGet(this::mapParent);
    }

    private TemplateReference mapParent() {
        return Optional.ofNullable(parentColumnDefinition)
                .map(ColumnDefinition::getTemplateReference)
                .map(TemplateReference::new)
                .orElse(null);
    }

    @Test
    public void test_case8_misuse() {
        // WHEN
        TemplateReference actual = misuse();

        // THEN
        assertThat(actual.getIdentifier(), is("regular"));
        assertThat(actual.getParameterValues(), hasEntry(equalTo("param"), equalTo("regular-param")));
    }
}
