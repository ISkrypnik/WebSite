package com.ilyaskrypnik.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@ApiModel(description = "Публикация")
@Entity
@Table(name = "publication")
@Data
@NoArgsConstructor
public class Publication {

    @ApiModelProperty(notes = "ID Публикации в базе данных", example = "123")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "Заголовок публикации", example = "Заголовок №1", required = true)
    @NonNull
    private String name;

    @ApiModelProperty(notes = "Текст публикации", example = "Содержимое", required = true)
    @NonNull
    private String text;

    //TODO add author field
}
