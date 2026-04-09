package com.javabff.bff_agendador_tarefas.business;

import com.javabff.bff_agendador_tarefas.business.Enums.StatusNotificacaoEnum;
import com.javabff.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.javabff.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.javabff.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void BuscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());

        log.info("iniciada a busca de tarefas");
        LocalDateTime horaAtual = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        List<TarefasDTOResponse> listaTarefas = tarefasService.buscaTarefasAgendadaPorPeriodo(horaAtual, horaFutura, token);
        log.info("Tarefas encontradas" + listaTarefas);

        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuário " + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
                    token);
        });
        log.info("Finalizado");
    }
    public String login(LoginRequestDTO dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequestDTO converterParaRequestDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
