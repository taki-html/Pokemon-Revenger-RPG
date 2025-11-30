const menuButtons = document.querySelectorAll(".menu-btn");
const screens = document.querySelectorAll(".screen");
const overlay = document.getElementById("overlay");

// Troca entre as abas do menu
menuButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        const target = btn.getAttribute("data-screen");
        trocarTela(target);
    });
});

function trocarTela(screenId) {
    screens.forEach(sec => {
        sec.classList.toggle("active", sec.id === `screen-${screenId}`);
    });
}

// Carrega dados iniciais do Java
async function carregarInicial() {
    try {
        const resp = await fetch("/api/inicial");
        const data = await resp.json();

        // Título/logo
        document.querySelector(".logo").textContent = data.titulo;

        // Resumo
        document.getElementById("texto-resumo").textContent = data.resumo;

        // Equipe
        const lista = document.querySelector(".team-list");
        lista.innerHTML = "";
        data.equipe.forEach(membro => {
            const li = document.createElement("li");
            li.innerHTML = `<strong>${membro.nome}</strong> – ${membro.papel}`;
            lista.appendChild(li);
        });
    } catch (e) {
        console.error("Erro ao carregar /api/inicial", e);
    }
}

// Botão "Iniciar Jogo": por enquanto só tira a película
document.getElementById("btn-iniciar-jogo").addEventListener("click", () => {
    overlay.classList.add("hidden");
    // aqui depois vocês vão chamar o backend pra começar o capítulo 1 de verdade
});

// Chama ao abrir a página
carregarInicial();
