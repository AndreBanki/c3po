# C3PO Delivery
## SENAI/SC - Pós Graduação Lato Sensu em Sistemas Web e Dispositivos Móveis
## Desenvolvimento de Aplicações Web (Java)

## Autores:
- André Luiz Bankin(andre.banki@gmail.com)
- Julio Izidoro da Silva Júnior (jizidoro@globo.com)

## Tecnologia utilizada:
- Back-end: Java (usando JPA)
- Front-End: Java Server Faces (usando Primefaces)

## Acesso:
- http://54.207.104.60/C3PO

## Detalhes para utilização (Visão "Cliente")
### Login
- login na página principal pelo botão "Entrar"
- verifica se o CPF é válido
- se for e não estiver cadastrado, abre uma tela de cadastro
- dessa tela redireciona para o Pedido
- se estiver cadastrado, entra direto em Pedido
### Pedido
- escolhe o produto na lista e adiciona ao carrinho com o "+"
- pode remover do carrinho com o "-"
- pode editar a quantidade clicando no nome
- Finalizar: completa o pedido e redireciona para "Obrigado" (cria o pedido com o funcionário default "DELIVERY")
- Cancelar: limpa o pedido corrente (enquanto não Finalizar ou Cancelar, volta sempre ao mesmo pedido)

## Detalhes para utilização (Visão "Funcionário")
### Login
- login na página principal pelo botão "Acesso restrito"
- verifica por login (CPF) e senha
- testar com login="91039061915" e senha="123456"
### Pedido
- igual à do Cliente, mas cria um pedido para o Funcionário locado e com o funcionário default "LOCAL"
### Cadastro - Funcionários
- edita clicando no nome
- verifica CPF válido e também se já existe outro funcionário com o mesmo CPF
### Cadastro - Clientes
- idem Funcionários
### Cadastro - Produtos
- edita com "Inline edit" na tabela
- verificar Valor > 0 e também se já existe outro produto com o mesmo nome
### Cadastro - Pedidos
- não edita, mas permite visualizar detalhes expandindo a linha
