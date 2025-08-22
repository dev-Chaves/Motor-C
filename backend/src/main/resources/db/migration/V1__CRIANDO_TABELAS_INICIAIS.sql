-- V1__create_initial_tables.sql

-- Tabela para armazenar os anexos (enum será mapeado como string)
CREATE TABLE anexo (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL UNIQUE
);

-- Inserir os valores do enum Anexo
INSERT INTO anexo (id, nome) VALUES
(1, 'ANEXO_1'),
(2, 'ANEXO_2'),
(3, 'ANEXO_3'),
(4, 'ANEXO_4'),
(5, 'ANEXO_5');

-- Tabela Empresa
CREATE TABLE empresa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cnpj VARCHAR(14) UNIQUE,
    razao_social VARCHAR(255),
    anexo_padrao VARCHAR(20),
    CONSTRAINT fk_empresa_anexo_padrao FOREIGN KEY (anexo_padrao) REFERENCES anexo(nome)
);

-- Tabela FaixaAnexo
CREATE TABLE faixa_anexo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    anexo_id BIGINT NOT NULL,
    receita_bruta_min DECIMAL(19,2) NOT NULL,
    aliquota_nominal DECIMAL(19,4) NOT NULL,
    data_inicio_vigencia DATE NOT NULL,
    data_fim_vigencia DATE,
    CONSTRAINT fk_faixa_anexo_anexo FOREIGN KEY (anexo_id) REFERENCES anexo(id)
);

-- Tabela Calculo (histórico)
CREATE TABLE calculo_historico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    empresa_id BIGINT NOT NULL,
    faixa_anexo_id BIGINT NOT NULL,
    data_calculo DATETIME NOT NULL,
    mes_referencia DATE NOT NULL,
    rpa DECIMAL(19,2) NOT NULL,
    rbt_12 DECIMAL(19,2) NOT NULL,
    aliquota_efetiva DECIMAL(19,2) NOT NULL,
    valor_das DECIMAL(19,2) NOT NULL,
    CONSTRAINT fk_calculo_empresa FOREIGN KEY (empresa_id) REFERENCES empresa(id),
    CONSTRAINT fk_calculo_faixa_anexo FOREIGN KEY (faixa_anexo_id) REFERENCES faixa_anexo(id)
);

-- Índices para melhor performance
CREATE INDEX idx_empresa_cnpj ON empresa(cnpj);
CREATE INDEX idx_faixa_anexo_anexo ON faixa_anexo(anexo_id);
CREATE INDEX idx_faixa_anexo_vigencia ON faixa_anexo(data_inicio_vigencia, data_fim_vigencia);
CREATE INDEX idx_calculo_empresa ON calculo_historico(empresa_id);
CREATE INDEX idx_calculo_mes_ref ON calculo_historico(mes_referencia);