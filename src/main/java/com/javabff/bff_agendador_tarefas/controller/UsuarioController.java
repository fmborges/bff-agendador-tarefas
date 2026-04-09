package com.javabff.bff_agendador_tarefas.controller;


import com.javabff.bff_agendador_tarefas.business.UsuarioService;
import com.javabff.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.javabff.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.javabff.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.javabff.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.javabff.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.javabff.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.javabff.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.javabff.bff_agendador_tarefas.infrastructure.SECURIRY.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name="Usuário", description = "Cadastro e login e usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "cria um novo usuário")
    @ApiResponse(responseCode = "200",description = "Usuário Salvo com sucesso")
    @ApiResponse(responseCode = "409",description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200",description = "Usuário Salvo com sucesso")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por email",
            description = "Busca dados do usuário")
    @ApiResponse(responseCode = "200",description = "Usuário encontrado")
    @ApiResponse(responseCode = "403",description = "Usuário Não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization",required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuários por ID", description = "Deleta usuário")
    @ApiResponse(responseCode = "200",description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "403",description = "Usuário Não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization",required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados de usuários",
            description = "Atualiza dados de usuário")
    @ApiResponse(responseCode = "200",description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403",description = "Usuário Não Cadastrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization",required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token,dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuários",
            description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200",description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization",required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto, token));
    }
    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuários",
            description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200",description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization",required = false) String token){

        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto , token));
    }
    @PostMapping("/endereco")
    @Operation(summary = "Salva endereço de usuários",
            description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200",description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "403",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization",required = false) String token){

        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de usuários",
            description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200",description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "403",description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500",description = "Erro de servidor")
    @ApiResponse(responseCode = "401",description = "Credenciais Inválidas")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization",required = false) String token){

        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,dto));
    }


}
