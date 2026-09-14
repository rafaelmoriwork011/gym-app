CREATE TABLE sexo (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao   VARCHAR(9) NOT NULL,
    CONSTRAINT uq_sexo_descricao UNIQUE (descricao)
);

CREATE TABLE estimulo (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao   VARCHAR(30) NOT NULL,
    CONSTRAINT uq_estimulo_descricao UNIQUE (descricao)
);

CREATE TABLE grupo_muscular (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao   VARCHAR(20) NOT NULL,
    CONSTRAINT uq_grupo_muscular_descricao UNIQUE (descricao)
);

CREATE TABLE treino_status (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao   VARCHAR(30) NOT NULL,
    CONSTRAINT uq_treino_status_descricao UNIQUE (descricao)
);

CREATE TABLE treino_configuracao_status (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao   VARCHAR(30) NOT NULL,
    CONSTRAINT uq_treino_configuracao_status_descricao UNIQUE (descricao)
);

CREATE TABLE treino_objetivo (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    descricao   VARCHAR(30) NOT NULL,
    CONSTRAINT uq_treino_objetivo_descricao UNIQUE (descricao)
);

CREATE TABLE usuario (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sexo_id         UUID NOT NULL,
    altura          NUMERIC(5,2) NOT NULL,
    peso_atual      NUMERIC(5,2) NOT NULL,
    data_nascimento DATE NOT NULL,
    data_inclusao   TIMESTAMP NOT NULL DEFAULT now(),
    data_alteracao  TIMESTAMP,
    CONSTRAINT fk_usuario_sexo FOREIGN KEY (sexo_id) REFERENCES sexo (id)
);

CREATE TABLE equipamento (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome            VARCHAR(70) NOT NULL,
    descricao       TEXT,
    data_inclusao   TIMESTAMP NOT NULL DEFAULT now(),
    data_alteracao  TIMESTAMP,
    CONSTRAINT uq_equipamento_nome UNIQUE (nome)
);

CREATE TABLE exercicio (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    grupo_muscular_id   UUID NOT NULL,
    nome                VARCHAR(70) NOT NULL,
    descricao           TEXT,
    data_inclusao       TIMESTAMP NOT NULL DEFAULT now(),
    data_alteracao      TIMESTAMP,
    CONSTRAINT uq_exercicio_nome UNIQUE (nome),
    CONSTRAINT fk_exercicio_grupo_muscular FOREIGN KEY (grupo_muscular_id) REFERENCES grupo_muscular (id)
);

CREATE TABLE treino_configuracao (
    id                             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id                     UUID NOT NULL,
    treino_configuracao_status_id  UUID NOT NULL,
    quantidade_treino_semana       INT NOT NULL,
    observacao                     TEXT,
    data_inclusao                  TIMESTAMP NOT NULL DEFAULT now(),
    data_alteracao                 TIMESTAMP,
    CONSTRAINT fk_treino_configuracao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id),
    CONSTRAINT fk_treino_configuracao_treino_configuracao_status FOREIGN KEY (treino_configuracao_status_id) REFERENCES treino_configuracao_status (id)
);

CREATE TABLE treino_configuracao_objetivo (
    id                          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    treino_objetivo_id          UUID NOT NULL,
    configuracao_treino_id      UUID NOT NULL,
    CONSTRAINT uq_treino_configuracao_objetivo UNIQUE (configuracao_treino_id, treino_objetivo_id),
    CONSTRAINT fk_treino_configuracao_objetivo_treino_objetivo FOREIGN KEY (treino_objetivo_id) REFERENCES
    treino_objetivo (id),
    CONSTRAINT fk_treino_configuracao_objetivo_configuracao_treino FOREIGN KEY (configuracao_treino_id) REFERENCES
    treino_configuracao (id)
);

CREATE INDEX idx_treino_configuracao_objetivo_configuracao_id ON treino_configuracao_objetivo (configuracao_treino_id);

CREATE TABLE treino (
    id                     UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    treino_configuracao_id UUID NOT NULL,
    nome                   VARCHAR(1) NOT NULL,
    ordem                  INT NOT NULL,
    CONSTRAINT fk_treino_treino_configuracao FOREIGN KEY (treino_configuracao_id) REFERENCES treino_configuracao (id)
);

CREATE INDEX idx_treino_treino_configuracao_id ON treino (treino_configuracao_id);

CREATE TABLE treino_exercicio (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    exercicio_id    UUID NOT NULL,
    treino_id       UUID NOT NULL,
    estimulo_id     UUID NOT NULL,
    equipamento_id  UUID,
    series          INT NOT NULL,
    repeticoes      INT NOT NULL,
    tempo_descanso  TIME NOT NULL,
    CONSTRAINT fk_treino_exercicio_exercicio FOREIGN KEY (exercicio_id) REFERENCES exercicio (id),
    CONSTRAINT fk_treino_exercicio_treino FOREIGN KEY (treino_id) REFERENCES treino (id),
    CONSTRAINT fk_treino_exercicio_estimulo FOREIGN KEY (estimulo_id) REFERENCES estimulo (id),
    CONSTRAINT fk_treino_exercicio_equipamento FOREIGN KEY (equipamento_id) REFERENCES equipamento (id)
);

CREATE INDEX idx_treino_exercicio_treino_id ON treino_exercicio (treino_id);
CREATE INDEX idx_treino_exercicio_exercicio_id ON treino_exercicio (exercicio_id);

CREATE TABLE treino_execucao (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    treino_id           UUID NOT NULL,
    treino_status_id    UUID NOT NULL,
    data_ocorrencia     TIMESTAMP NOT NULL,
    data_inclusao       TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_treino_execucao_treino FOREIGN KEY (treino_id) REFERENCES treino (id),
    CONSTRAINT fk_treino_execucao_status FOREIGN KEY (treino_status_id) REFERENCES treino_status (id)
);

CREATE INDEX idx_treino_execucao_treino_id ON treino_execucao (treino_id);
CREATE INDEX idx_treino_execucao_data_ocorrencia ON treino_execucao (data_ocorrencia);


INSERT INTO sexo (id, descricao) VALUES
    ('a0000000-0000-4000-8000-000000000001', 'Masculino'),
    ('a0000000-0000-4000-8000-000000000002', 'Feminino');

INSERT INTO estimulo (id, descricao) VALUES
    ('b0000000-0000-4000-8000-000000000001', 'Hipertrofia'),
    ('b0000000-0000-4000-8000-000000000002', 'Força'),
    ('b0000000-0000-4000-8000-000000000003', 'Resistência');

INSERT INTO grupo_muscular (id, descricao) VALUES
    ('c0000000-0000-4000-8000-000000000001', 'Peitoral'),
    ('c0000000-0000-4000-8000-000000000002', 'Costas'),
    ('c0000000-0000-4000-8000-000000000003', 'Quadríceps'),
    ('c0000000-0000-4000-8000-000000000004', 'Posterior de Coxa'),
    ('c0000000-0000-4000-8000-000000000005', 'Glúteos'),
    ('c0000000-0000-4000-8000-000000000006', 'Ombros'),
    ('c0000000-0000-4000-8000-000000000007', 'Bíceps'),
    ('c0000000-0000-4000-8000-000000000008', 'Tríceps'),
    ('c0000000-0000-4000-8000-000000000009', 'Panturrilhas'),
    ('c0000000-0000-4000-8000-000000000010', 'Abdômen'),
    ('c0000000-0000-4000-8000-000000000011', 'Antebraços'),
    ('c0000000-0000-4000-8000-000000000012', 'Adutores');

INSERT INTO treino_status (id, descricao) VALUES
    ('d0000000-0000-4000-8000-000000000001', 'Finalizado'),
    ('d0000000-0000-4000-8000-000000000002', 'Dispensado');

INSERT INTO treino_configuracao_status (id, descricao) VALUES
    ('e0000000-0000-4000-8000-000000000001', 'Ativo'),
    ('e0000000-0000-4000-8000-000000000002', 'Finalizado');

INSERT INTO treino_objetivo (id, descricao) VALUES
    ('f0000000-0000-4000-8000-000000000001', 'Aumento de Massa Muscular'),
    ('f0000000-0000-4000-8000-000000000002', 'Emagrecimento'),
    ('f0000000-0000-4000-8000-000000000003', 'Condicionamento Físico');