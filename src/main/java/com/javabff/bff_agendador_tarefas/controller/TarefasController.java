package com.javabff.bff_agendador_tarefas.controller;


import com.javabff.bff_agendador_tarefas.business.Enums.StatusNotificacaoEnum;
import com.javabff.bff_agendador_tarefas.business.TarefasService;
import com.javabff.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.javabff.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.javabff.bff_agendador_tarefas.infrastructure.SECURIRY.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name="Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa do usuário")
    @ApiResponse(responseCode = "200",description = "Tarefa Salva com sucesso")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca taredas por período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200",description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization",required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadaPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar Lista de tarefas por email de usuário",
            description = "Busca lista de tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200",description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization",required = false) String token) {
        List<TarefasDTOResponse> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por ID",
            description = "Deleta tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200",description = "Tarefas Deletadas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorID(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization",required = false) String token){
        tarefasService.deletaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera Status da tarefas ",
            description = "Altera Status das tarefas cadastradas")
    @ApiResponse(responseCode = "200",description = "Status da Tarefa Alterado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization",required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas ",
            description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200",description = "Tarefas Alteradas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization",required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }


}
