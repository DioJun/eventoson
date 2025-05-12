Trabalho realizado para a faculdade de 'Sistemas para a Internet'

# Sistema de Gerenciamento de Eventos (Java Console)

Este é um sistema de gerenciamento de eventos desenvolvido em Java, com interface de linha de comando. Ele permite que os usuários gerenciem eventos pessoais, incluindo cadastro, listagem, exclusão e atualização de eventos. Os dados são armazenados em um arquivo de texto para garantir persistência entre sessões.

## Funcionalidades

- ✅ Cadastro de eventos com nome, endereço, categoria, data/hora e descrição.
- ✅ Listagem de todos os eventos cadastrados.
- ✅ Listagem de eventos futuros (apenas eventos com data/hora posterior ao momento atual).
- ✅ Exclusão de eventos por índice.
- ✅ Atualização de eventos existentes.
- ✅ Persistência de dados em arquivo (`events.data`).
- ✅ Validação de data e hora no momento do cadastro.

## Estrutura do Projeto

- `src/Main.java`: Classe principal que inicializa o sistema.
- `src/view/Sistema.java`: Gerencia a interação com o usuário.
- `src/controller/GerenciadorEventos.java`: Controla as operações de CRUD e manipulação de eventos.
- `src/model/Evento.java`: Representa a entidade "Evento".
- `src/model/Usuario.java`: Representa a entidade "Usuário".
- `src/utils/Utils.java`: Contém utilitários, como formatação de data/hora.

## Requisitos

- **Java 8** ou superior.
- IDE ou terminal com suporte à compilação e execução de código Java.
