package com.sika.code.batch.animal;

import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;

/**
 * @author daiqi
 * @create 2019-09-12 23:09
 */
@Data
public class AnimalDTO extends BaseStandardDTO {
    private Integer name;
    private String color;
}