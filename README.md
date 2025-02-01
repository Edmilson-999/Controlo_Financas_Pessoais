# Finanças Pessoais

Aplicativo de gerenciamento de finanças pessoais que permite o acompanhamento de receitas e despesas, definição de metas de gastos, e notificações diárias sobre o status financeiro.

## Funcionalidades

- **Adicionar Receita:** Permite ao usuário adicionar uma nova receita, incluindo informações como descrição, valor, categoria e data.
- **Adicionar Despesa:** Permite ao usuário adicionar uma nova despesa, com informações detalhadas.
- **Visualizar Transações:** Lista todas as transações (receitas e despesas) com detalhes e categorização.
- **Notificações Diárias:** Notificações diárias sobre o status financeiro.
- **Definição de Metas:** Permite ao usuário definir uma meta de gasto mensal.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Framework:** Android SDK
- **Banco de Dados:** Room Database
- **Gerenciamento de Tarefas:** WorkManager para notificações periódicas
- **Interface Gráfica:** RecyclerView, LinearLayout

## Estrutura do Projeto

- **`MainActivity.java`**: Tela principal com opções para adicionar receita, despesa e visualizar transações.
- **`Transacao.java`**: Classe modelo para representar transações (receitas ou despesas).
- **`TransacaoAdapter.java`**: Adaptador para exibir a lista de transações em um RecyclerView.
- **`UserSettings.java`**: Classe para armazenar as configurações do usuário, como meta de gastos mensais.
- **`UserSettingsDao.java`**: DAO para acessar as configurações do usuário no banco de dados Room.
- **`NotificationWorker.java`**: Classe responsável por gerenciar as notificações diárias.
- **`MetaNotificationWorker.java`**: Classe para notificações relacionadas à meta de gastos.

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/Edmilson-999/Controlo_Financas_Pessoais.git
