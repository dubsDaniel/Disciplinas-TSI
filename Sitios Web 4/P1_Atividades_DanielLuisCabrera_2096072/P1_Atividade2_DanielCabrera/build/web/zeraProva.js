/* 
Não consegui fazer funcionar, deveria chamar o @PreDestroy quando acontecesse o evento beforeunload (ao usuário fechar o navegador)

window.addEventListener("beforeunload", function(event)) {
    fetch('src/controle/ProvaBean/zerando',
    {method: 'POST'});
    event.preventDefault();
});
*/