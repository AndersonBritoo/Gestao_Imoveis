# 1. Introdução

Uma empresa imobiliária pretende informatizar a sua gestão interna, de forma a controlar os imóveis sob angariação, os agentes responsáveis e as vendas realizadas.

Pretende-se que elabore uma aplicação em Java que auxilie a equipa na gestão do portefólio de imóveis, angariações, comissões e registos de vendas, cumprindo os requisitos apresentados abaixo.

# 2. Descrição da aplicação e requisitos

A formatação e apresentação dos menus na consola fica ao critério do aluno ou grupo de trabalho.

## 2.1 Empresa

A imobiliária gere um conjunto de imóveis para venda e arrendamento, angariados por diferentes agentes.

Por questões de gestão e simplicidade, o sistema deve permitir o registo de até 50 imóveis ativos e até 10 agentes imobiliários.

## 2.2 Imóveis

Cada imóvel é identificado por um código único (5 dígitos positivos) e apresenta as seguintes características:

- Código do imóvel (ID) — único e positivo;
- Tipo de imóvel (Apartamento, Moradia, Terreno, Loja, Escritório);
- Localização (cidade ou zona);
- Área útil (m²);
- Preço de venda (€);
- Estado (Disponível, Reservado, Vendido);
- Agente responsável (ligação a um agente registado);
- Comissão (%) atribuída ao agente;

## 2.3 Agentes

Cada agente imobiliário deve possuir:

- Código do agente (ID) — único e positivo (3 dígitos);
- Nome;
- NIF;
- Total de vendas realizadas (€);
- Total de comissão acumulada (€).

## 2.4 Vendas

Cada venda realizada está associada a um imóvel e contém:

- Número da venda (ID);
- Código do imóvel;
- Código do agente;
- Data da venda;
- Valor final da venda (€);
- Comissão calculada (€);

# 3. Objetivos

A aplicação deve permitir:

1. Registar agentes imobiliários com respetivos dados.
2. Registar imóveis, associando-os a um agente responsável.
3. Atualizar informação de um imóvel, incluindo preço, estado ou agente responsável.
4. Listar imóveis por tipo ou por agente.
5. Pesquisar imóveis com base em critérios (localização, tipo, preço máximo, estado).
6. Registar vendas, com cálculo automático da comissão do agente.
7. Listar vendas realizadas e total de comissões pagas por agente.
8. Calcular estatísticas — total de imóveis ativos, imóveis vendidos, volume de vendas total.
9. Remover imóveis (individualmente ou todos).
10. Listar agentes com respetiva performance (vendas e comissões acumuladas).

# 4. Testes (exemplos)

Para efeitos de testes, devem existir:

- Pelo menos 10 imóveis registados (com tipos e localizações diferentes);
- Pelo menos 3 agentes registados;
- Pelo menos 3 vendas realizadas, com cálculo de comissões distinto para cada agente;
- Todos os imóveis devem ter informações completas e um estado coerente (Disponível, Reservado ou Vendido).

# 5. Funcionalidades detalhadas

1. Registar um agente
   1. No menu, escolher "Registar agente".
   2. Introduzir o código do agente, nome e NIF.
   3. Mostrar confirmação com os dados introduzidos.
2. Registar um imóvel
   1. No menu, escolher "Registar imóvel".
   2. Introduzir o código do imóvel e características completas.
   3. Associar a um agente (indicando o código do agente).
   4. Mostrar a informação registada.
3. Atualizar informação de imóvel
   1. No menu, escolher "Atualizar imóvel".
   2. Inserir o código do imóvel e os campos a alterar (ex.: preço, estado, agente responsável).
   3. Mostrar a informação atualizada.
4. Listar imóveis
   1. No menu, escolher "Listar imóveis".
   2. Permitir listar:
      1. Todos os imóveis;
      2. Por tipo (ex.: apenas apartamentos);
      3. Por agente responsável.
5. Pesquisa de imóveis
   1. No menu, escolher "Filtrar imóveis".
   2. Mostrar opções encadeadas de filtragem: (ex.: Tipo, Localização, Preço máximo, estado).
   3. Mostrar os imóveis que correspondem aos critérios inseridos.
6. Registar uma venda
   1. No menu, escolher "Registar venda".
   2. Inserir o código do imóvel e do agente.
   3. Introduzir o valor final da venda.
   4. Calcular a comissão automaticamente com base na percentagem definida no imóvel.
   5. Atualizar o estado do imóvel para "Vendido" e atualizar os totais do agente.
   6. Mostrar resumo da venda e valores calculados.
7. Listar vendas
   1. No menu, escolher "Listar vendas".
   2. Mostrar todas as vendas registadas, incluindo valores e agentes envolvidos.
8. Estatísticas gerais
   1. No menu, escolher "Estatísticas".
   2. Mostrar:
      1. Total de imóveis registados;
      2. Total de imóveis vendidos;
      3. Volume total de vendas (€);
      4. Comissões totais pagas (€).
9. Remover imóveis
   1. No menu, escolher "Remover imóvel".
   2. Inserir o código do imóvel a remover.
   3. Permitir também "Desocupação total" (remoção de todos os imóveis).
10. Listar performance dos agentes
    1. No menu, escolher "Listar agentes".
    2. Mostrar, para cada agente:
       1. Nome;
       2. Total de imóveis angariados;
       3. Total de vendas realizadas;
       4. Comissão acumulada (€).

# 6. Observações

- Deverá criar as estruturas de dados necessárias;
- A validação de dados será valorizada;
- O trabalho deve ser implementado em **linguagem JAVA** e deve apenas usar os **<u>conhecimentos lecionados nas aulas de Introdução à Programação</u>**;
- A funcionalidade do programa é, nesta unidade curricular, muito mais importante que os aspetos estéticos. Será valorizada a clareza e simplicidade do código. Deverá assim estruturar o programa de modo a torná-lo simples, bem estruturado, e sem repetições desnecessárias de código. Será ainda valorizada a conveniente indentação do código e a inclusão de comentários, sempre que tal for interessante do ponto de vista da interpretação;
- Possíveis melhorias incluídas no programa, devidamente fundamentadas e enquadradas com o programa desta unidade curricular, serão consideradas e valorizadas;
- Deve ser elaborado um relatório que descreva o trabalho produzido e as funcionalidades implementadas;
- Não serão admitidos plágios, mesmo que parciais - trabalhos copiados terão nota ZERO (tanto quem copiou como quem deixou copiar);
- Caso se revele necessário, poderão ser feitas atualizações ou alterações a este enunciado, pelo que os alunos deverão estar atentos a esta eventualidade. Qualquer atualização ou alteração será devidamente anunciada no Moodle desta unidade curricular.

# 7. Constituição dos grupos

Os grupos de trabalho deverão ser constituídos por um mínimo de 3 e um máximo de 4 estudantes.

# 8. Material a submeter no Moodle

Um dos alunos do grupo deverá submeter **via moodle até à data limite,** os seguintes elementos:

- Ficheiro com identificação dos alunos do grupo: nomes e números de aluno (\*.txt);
- Arquivo compactado com todos os ficheiros com o código fonte da aplicação (\*zip, \*rar);
- Ficheiro com dados criados e carregados no arranque na aplicação (\*txt);

# 9. Apresentação, defesa e avaliação

A defesa do projeto é realizada por sprints, com a presença de todos os elementos do grupo de trabalho, durante as aulas de desenvolvimento do trabalho de projeto.

A avaliação, individual, tem em consideração as seguintes componentes:

1. Funcionamento geral da aplicação (app a correr), i.e., utilização fácil e intuitiva por parte do utilizador, aplicação com capacidade de ultrapassar erros de entrada de dados ou sem estourar, apresentação clara dos resultados, etc.;
2. Estrutura do código da app, i.e., as opções tomadas para resolver o problema proposto, o código comentado, indentado, escrito segundo a convenção, a arrumação do código em funções, utilização de tipos de dados e estruturas adequadas, etc.;
3. Não se aceitam apps que não sejam implementadas em **linguagem JAVA** e que apresentem erros de compilação no dia da defesa;
