package com.epam.mix.optionaldemo.case8.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateReference {
    private String identifier;
    private Map<String, String> parameterValues;

    public TemplateReference(TemplateReference templateReference) {
        this.identifier = templateReference.getIdentifier();
        this.parameterValues = new LinkedHashMap<>(templateReference.getParameterValues());
    }
}
