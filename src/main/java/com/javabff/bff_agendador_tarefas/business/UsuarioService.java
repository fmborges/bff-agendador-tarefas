package com.javabff.bff_agendador_tarefas.business;


import com.javabff.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.javabff.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.javabff.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.javabff.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.javabff.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.javabff.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.javabff.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.javabff.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;


    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvaUsuario(usuarioDTO);
    }


    public String loginUsuario(LoginRequestDTO usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }



    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }



    public void deletaUsuarioPorEmail(String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto){

        return usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.atualizaEndereco(enderecoDTO, idEndereco,token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){

        return usuarioClient.atualizaTelefone(telefoneDTO,idTelefone,token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto){
        return usuarioClient.cadastraEndereco(dto,token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){
        return usuarioClient.cadastraTelefone(dto, token);
    }



}
