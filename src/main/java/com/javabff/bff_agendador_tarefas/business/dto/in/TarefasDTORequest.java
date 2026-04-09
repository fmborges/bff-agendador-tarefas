package com.javabff.bff_agendador_tarefas.business.dto.in;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.javabff.bff_agendador_tarefas.business.Enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTORequest {

    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;


}
