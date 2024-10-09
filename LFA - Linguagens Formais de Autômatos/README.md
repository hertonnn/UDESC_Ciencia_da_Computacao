
# Linguagens Formais de Autômatos

![img_lfa](https://github.com/hertonnn/UDESC_Ciencia_da_Computacao/blob/master/utils/img/img_lfa.jpg)

Entende-se por Teoria das Linguagens Formais e dos Autômatos o estudo de modelos
matemáticos que possibilitam a especificação e o reconhecimento de linguagens (no sentido
amplo da palavra), suas classificações, estruturas, propriedades, características e interrelacionamentos.
A importância desta Teoria na Ciência da Computação é dupla: Ela tanto apóia outros
aspectos teóricos da Ciência da Computação (decidibilidade, computabilidade, complexidade
computacional, por exemplo), como fundamenta diversas aplicações computacionais tais
como processamento de linguagens, reconhecimento de padrões, modelagem de sistemas. 

## Material Complementar

- [Aplicação Web para apoio de cursos de Linguagens Formais e Teoria dos Autómatos - OFLAP](http://ctp.di.fct.unl.pt/FACTOR/OFLAT/)

- [JFLAP - Software for experimenting with formal languages topic](https://www.jflap.org/jflaptmp/)

- [Online Regex tester and debuger - regex101](https://regex101.com)

- [Finite State Machine Designer - FSM](https://ricardofm.net/site/fsm-tool)

- [FSM v2](https://markusfeng.com/projects/graph/)

- Graph Visualization Software - GraphViz - HP do projeto - Ferramenta online

   Exemplo 1 (GraphViz) - [Resultados via Web](https://dreampuf.github.io/GraphvizOnline/#digraph%20finite_state_machine%20%7B%0A%20%20%20%20rankdir%3DLR%3B%0A%20%20%20%20size%3D%228%2C5%22%0A%0A%20%20%20%20node%20%5Bshape%20%3D%20doublecircle%5D%3B%20S%3B%0A%20%20%20%20node%20%5Bshape%20%3D%20point%5D%3B%20qi%0A%0A%20%20%20%20node%20%5Bshape%20%3D%20circle%5D%3B%0A%20%20%20%20qi%20-%3E%20S%3B%0A%20%20%20%20S%20%20-%3E%20q1%20%5B%20label%20%3D%20%22b%22%20color%3D%22red%3Ablue%22%5D%3B%0A%20%20%20%20S%20%20-%3E%20S%20%20%5B%20label%20%3D%20%22a%22%20%5D%3B%0A%20%20%20%20q1%20-%3E%20S%20%20%5B%20label%20%3D%20%22a%22%20%5D%3B%0A%20%20%20%20q1%20-%3E%20q2%20%5B%20label%20%3D%20%22b%22%20%5D%3B%0A%20%20%20%20q2%20-%3E%20S%20%5B%20label%20%3D%20%22a%22%20%5D%3B%0A%20%20%20%20q2%20-%3E%20q2%20%5B%20label%20%3D%20%22b%22%20%5D%3B%0A%7D)

  Exemplo 2 (GraphViz) - [Resultados via Web](https://dreampuf.github.io/GraphvizOnline/#digraph%20G%20%7B%0A%20%20rankdir%3DLR%0A%20%20subgraph%20finite_state_machine_AFN%20%7B%0A%20%20%20%20rankdir%3DLR%3B%0A%20%20%20%20size%3D%222.0%22%0A%0A%20%20%20%20node%20%5Bshape%20%3D%20circle%5D%3B%20q0%3B%0A%20%20%20%20node%20%5Bshape%20%3D%20none%5D%3B%20AFN%0A%20%20%20%20node%20%5Bshape%20%3D%20doublecircle%5D%3B%0A%20%20%20%20AFN%20-%3E%20q0%3B%0A%20%20%20%20q0%20%20-%3E%20q1%20%5B%20label%20%3D%20%22a%22%20color%3D%22red%22%20style%3Ddashed%5D%3B%0A%20%20%20%20q0%20%20-%3E%20q0%20%5B%20label%20%3D%20%22a%22%20color%3D%22red%22%20style%3Ddashed%5D%3B%0A%20%20%20%20q1%20-%3E%20q1%20%20%5B%20label%20%3D%20%22b%22%20%5D%3B%0A%20%20%7D%0A%0Asubgraph%20finite_state_machine_AFD%20%7B%0A%20%20%20%20rankdir%3DLR%3B%0A%20%20%20%20size%3D%222.8%22%0A%0A%20%20%20%20node%20%5Bshape%20%3D%20none%5D%3B%20AFD%0A%20%20%20%20node%20%5Bshape%20%3D%20circle%5D%3B%20qa%3B%0A%20%20%20%20node%20%5Bshape%20%3D%20doublecircle%5D%3B%20qb%3B%20qc%3B%0A%20%20%20%20AFD%20-%3E%20qa%3B%0A%20%20%20%20qa%20%20-%3E%20qb%20%5B%20label%20%3D%20%22a%22%20%20%5D%3B%0A%20%20%20%20qb%20%20-%3E%20qc%20%20%5B%20label%20%3D%20%22b%22%20%20%5D%3B%0A%20%20%20%20qb%20-%3E%20qb%20%20%5B%20label%20%3D%20%22a%22%20%5D%3B%0A%20%20%20%20qc%20-%3E%20qc%20%5B%20label%20%3D%20%22b%22%20%5D%3B%0A%20%20%7D%0A%7D)

- [Simulador/Visualizador: AFDs, AFNs e AFεs - FSM Simulator](http://ivanzuzak.info/noam/webapps/fsm_simulator/)

- [Simulador de Autômatos - Automaton Simulator (em fase de testes...)](https://web.cs.ucdavis.edu/~doty/automata/)

- [Simulador de Autômatos (inclusive com pilha) - Automaton Simulator](http://ricardofm.net/site/automaton-simulator-tool) (obs.: não distingue a de A)

- [Simulador de Autômatos (inclusive com pilha) - AutoSim (em fase de testes...)](http://www.cburch.com/proj/autosim/)

- [Ferramenta para simplificação/padronização/reconhecimento de linguagens (gramáticas)](https://ricardofm.net/site/cyk-tool)

- [DFA Minimizer - Minimização de AFDs (em fase de testes...)](https://aswaddev.github.io/dfa-minimizer/)

- [Finite Automata Designer - Algoritmos aplicados em AFDs (NOVA FERRAMENTA!!! em fase de testes... adicionada em 19/04/2024)](https://fa.akdev.ir/)

### Sobre o Professor
Prof. Ricardo Martins

Possui graduação em Engenharia Elétrica pela Universidade Federal de Uberlândia (1993), mestrado em Engenharia Elétrica pela Universidade Federal de Santa Catarina (1996) e doutorado em Engenharia Elétrica pela Universidade do Estado de Santa Catarina (2020). Desde 1997, é professor efetivo da Universidade do Estado de Santa Catarina - UDESC, lotado no Departamento de Ciência da Computação. Tem experiência nas áreas de Ciência da Computação e Engenharia Elétrica, com ênfase em Controle e Automação de Sistemas. Atua principalmente nos seguintes temas: Linguagem Formais e Autômatos, Redes de Computadores, Controle Supervisório de Sistemas a Eventos Discretos. Atualmente, desenvolve trabalhos relacionados a Sistemas Autônomos aplicados à veículos não tripulados.
 
Dep. de Ciência da Computação - DCC
Sala 20 / F-210E (interna) - Ramal 17823
Fone: (47) 3481-7823
 
Áreas de atuação
Linguagens Formais e Autômatos
Controle / Coordenação (veículos não tripulados)
 
Disciplinas ministradas
- LFA0001 - BCC
- LPG0001 - BCC
- LPG0002 - TADS
- ECS1004 e ECS2004 - TADS
(Coordenação de Estágios)
### Email:
ricardo.martins@udesc.br

 
### Lattes:
http://lattes.cnpq.br/4395588770660219


