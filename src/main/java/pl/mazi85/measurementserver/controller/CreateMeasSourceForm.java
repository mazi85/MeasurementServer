package pl.mazi85.measurementserver.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter @Getter
public class CreateMeasSourceForm {


    private String name;

    private String ip;

    private String port;

    private Long commProtocolId;
}
