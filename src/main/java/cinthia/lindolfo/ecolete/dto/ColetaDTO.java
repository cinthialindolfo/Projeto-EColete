package cinthia.lindolfo.ecolete.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ColetaDTO {
    @NotEmpty(message = "Descrição do material é obrigatória")
    private String descricaoMaterial;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade deve ser pelo menos 1")
    private Integer quantidadeMaterial;

    @NotNull(message = "Data de agendamento é obrigatória")
    @Future(message = "Data deve ser futura")
    private LocalDateTime dataAgendamento;

    public String getDescricaoMaterial() {
        return descricaoMaterial;
    }

    public void setDescricaoMaterial(String descricaoMaterial) {
        this.descricaoMaterial = descricaoMaterial;
    }

    public Integer getQuantidadeMaterial() {
        return quantidadeMaterial;
    }

    public void setQuantidadeMaterial(Integer quantidadeMaterial) {
        this.quantidadeMaterial = quantidadeMaterial;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}