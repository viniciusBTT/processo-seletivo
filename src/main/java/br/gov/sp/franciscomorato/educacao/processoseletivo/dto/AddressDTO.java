package br.gov.sp.franciscomorato.educacao.processoseletivo.dto;

/*** espelho da api */
public record AddressDTO (
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    String ibge,
    String gia,
    String ddd,
    String siafi
){}