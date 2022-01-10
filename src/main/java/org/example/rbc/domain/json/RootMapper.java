package org.example.rbc.domain.json;

import java.util.List;
import lombok.Data;

@Data
public class RootMapper {
    private List<EntityMapper> entities;
}
