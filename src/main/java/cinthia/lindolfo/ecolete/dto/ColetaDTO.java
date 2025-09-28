package cinthia.lindolfo.ecolete.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ColetaDTO {
    private Long id;
    
    @NotBlank(message = "Descrição do material é obrigatória")
    private String descricaoMaterial;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser pelo menos 1")
    private Integer quantidadeMaterial;

    @NotNull(message = "Data de agendamento é obrigatória")
    @Future(message = "Data deve ser futura")
    private LocalDateTime dataAgendamento;
    
    private LocalDateTime dataConclusao;
    private String status;
    private Long usuarioId;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getDescricaoMaterial() { return descricaoMaterial; }
    public void setDescricaoMaterial(String descricaoMaterial) { this.descricaoMaterial = descricaoMaterial; }
    
    public Integer getQuantidadeMaterial() { return quantidadeMaterial; }
    public void setQuantidadeMaterial(Integer quantidadeMaterial) { this.quantidadeMaterial = quantidadeMaterial; }
    
    public LocalDateTime getDataAgendamento() { return dataAgendamento; }
    public void setDataAgendamento(LocalDateTime dataAgendamento) { this.dataAgendamento = dataAgendamento; }
    
    public LocalDateTime getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDateTime dataConclusao) { this.dataConclusao = dataConclusao; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}