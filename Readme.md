# DexGo

O **DexGo** é um aplicativo que permite aos usuários explorar e listar os Pokémons existentes, com funcionalidades para filtrar por ID, tipo ou nome. 

Os usuários podem selecionar um Pokémon na lista para visualizar detalhes completos, além de duas versões do mesmo (versão shiny e normal).

## Funcionalidades

- **Listagem de Pokémon**: Exibe uma lista de Pokémon com opções de filtro por ID, tipo ou nome.
- **Detalhes do Pokémon**: Permite visualizar informações detalhadas de um Pokémon ao clicar na lista.
- **Carregamento Eficiente**: Utiliza PagingSource para uma navegação suave através dos dados dos Pokémon.

## Tecnologias Utilizadas

- **PagingSource**: Para implementar a paginação eficiente dos dados dos Pokémon.
- **ViewModels**: Para gerenciar os dados e preservar o estado da interface durante mudanças de configuração.
- **DataBinding**: Facilita a ligação entre a lógica de dados e a interface do usuário.
- **LiveData**: Para observar mudanças nos dados e atualizar a interface dinamicamente.
- **Flow**: Para gerenciar e emitir dados assíncronos.
- **Retrofit**: Para realizar chamadas de rede e buscar dados da API.
- **Hilt**: Para injeção de dependências, simplificando a configuração e o gerenciamento dos componentes.

## Imagens e Ícones

- **Ícones**: Foram utilizados ícones do [Google Material Icons](https://material.io/resources/icons/).
- **Logo e símbolo da pokebola**: Obtida dos seguintes sites:
  - [Pokeball Icon 1](https://thenounproject.com/icon/pokeball-640381/)
  - [Pokeball Icon 2](https://thenounproject.com/icon/pokeball-584814/)

## Instruções de Uso

1. **Instalação**:
    ```bash
    git clone https://https://github.com/sntsbia/my-pokedex
    cd pokedex-pokedex
    ./gradlew build
    ```

2. **Execução**: Abra o projeto no [Android Studio](https://developer.android.com/studio) e execute o aplicativo em um dispositivo ou emulador Android.

3. **Requisitos**: Certifique-se de que o dispositivo esteja conectado à internet para acessar os dados dos Pokémon.

## Futuras Implementações

- **Cache Offline**: Implementação de cache para permitir o uso do aplicativo sem conexão com a internet.
- **Modo de Disputas**: Adição de funcionalidades para disputas entre Pokémon.

## Contribuições

Sinta-se à vontade para contribuir com melhorias e correções.

---

Este projeto está em constante desenvolvimento e novas funcionalidades serão adicionadas.

Beatriz Santos (@sntsb)