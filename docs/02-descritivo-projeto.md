# 1. Descritivo do Projeto

## Contexto

A TOTVS é a maior empresa de tecnologia do Brasil e atua como *trusted advisor* de seus
clientes, oferecendo sistemas e plataformas para empresas de todos os portes. No dia a dia
comercial, equipes de venda e de relacionamento realizam diversas reuniões com clientes, e
o conteúdo dessas conversas — registrado em transcrições — carrega informações valiosas
que normalmente não são aproveitadas de forma estruturada: produtos citados, sinais de
satisfação ou insatisfação, oportunidades de upsell, ameaças de troca para concorrentes e
o perfil do interlocutor.

## Problema

Sem uma ferramenta de apoio, esse conteúdo permanece como "ruído": disperso em anotações,
gravações e e-mails, dependendo da memória e da disponibilidade do vendedor para ser
resgatado. Isso dificulta o acompanhamento de risco de churn, a priorização de
oportunidades de venda e a tomada de decisão por gestores comerciais.

## Objetivo da Solução

O **TOTVS Meeting Intelligence** tem como objetivo transformar transcrições de reuniões
comerciais em inteligência de negócio acionável. A solução permite cadastrar clientes e
reuniões, registrar a transcrição de cada conversa, executar uma análise automatizada
sobre esse texto e gerar insights de negócio a partir dela. Com isso, o sistema endereça
diretamente os três pontos centrais do desafio técnico proposto: (1) identificação de
oportunidades comerciais (gatilhos de compra/upsell), (2) sinalização de risco de retenção
(frases que indiquem insatisfação, reclamação ou intenção de troca) e (3) mapeamento do
ecossistema de produtos TOTVS citados na conversa (Protheus, RM, Fluig, Datasul, Winthor).

## Justificativa das Escolhas

A modelagem foi pensada para refletir o domínio de negócio com Orientação a Objetos e
princípios de Domain Driven Design. A classe abstrata `Pessoa` concentra os atributos
comuns a quem participa do processo (id e nome), sendo especializada em `Cliente` (com
segmento, porte e o histórico de reuniões) e `UsuarioSistema` (o colaborador responsável
pela reunião, com seu perfil de atuação). Cada `Reuniao` referencia o cliente, o
responsável, sua `Transcricao` e o conjunto de `AnaliseIA` geradas a partir dela. A
`AnaliseIA` carrega o sentimento geral e os scores de risco e oportunidade calculados pelo
`AnaliseService`, além da lista de `Insight` produzidos pelo `InsightService` — cada
insight podendo estar associado a um `ProdutoTotvs` do catálogo, o que viabiliza o
mapeamento de ecossistema citado acima.

## Aplicação no Negócio

Com essa estrutura, um gestor comercial consegue, a partir de uma única transcrição,
identificar rapidamente se um cliente está satisfeito ou insatisfeito, se há risco de
cancelamento ou migração para a concorrência, quais módulos TOTVS já estão em uso ou foram
mencionados como oportunidade, e priorizar as próximas ações junto a esse cliente —
exatamente a proposta de "transformar o ruído da conversa em inteligência acionável"
descrita no desafio.
