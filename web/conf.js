// Referências globais
const menuButtons = document.querySelectorAll(".menu-btn");
const screens = document.querySelectorAll(".screen");
const overlay = document.getElementById("overlay");

let idSessaoAtual = null; // Guarda o ID da sua partida

// --- LÓGICA DO MENU ---
menuButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        const target = btn.getAttribute("data-screen");
        
        // Remove 'active' de todas e adiciona na alvo
        screens.forEach(sec => sec.classList.remove("active"));
        document.getElementById(`screen-${target}`).classList.add("active");
    });
});

// --- LÓGICA DO JOGO ---

// 1. Função que monta a tela de chat (remove o botão Iniciar e põe o histórico)
function configurarAreaDeJogo() {
    const divJogo = document.getElementById("screen-play");
    
    // Substitui o conteúdo da div 'screen-play' pelo Chat
    divJogo.innerHTML = `
        <div id="historico-jogo" style="
            height: 400px; 
            overflow-y: auto; 
            background: rgba(0,0,0,0.6); 
            padding: 15px; 
            border-radius: 8px;
            margin-bottom: 15px; 
            font-size: 1.1rem;
            line-height: 1.5;
            white-space: pre-line;
            border: 1px solid #444;">
            <p style="color: yellow;">Carregando história...</p>
        </div>

        <div style="display: flex; gap: 10px;">
            <input type="text" id="input-jogador" placeholder="Digite sua escolha (Ex: 1) e aperte Enter..." 
                style="flex: 1; padding: 12px; border-radius: 5px; border: none; font-size: 1rem;">
            <button id="btn-enviar" style="padding: 10px 20px; cursor: pointer; font-weight: bold;">Enviar</button>
        </div>
    `;

    // Adiciona eventos no novo botão e no input
    document.getElementById("btn-enviar").addEventListener("click", enviarAcao);
    document.getElementById("input-jogador").addEventListener("keypress", function (e) {
        if (e.key === 'Enter') enviarAcao();
    });
}

// 2. Função auxiliar para escrever no chat
function adicionarAoHistorico(texto, ehJogador) {
    const historico = document.getElementById("historico-jogo");
    
    // Se for a primeira mensagem, limpa o "Carregando..."
    if (historico.innerText.includes("Carregando história...")) {
        historico.innerHTML = "";
    }

    const p = document.createElement("div");
    p.style.marginBottom = "10px";
    p.style.padding = "10px";
    p.style.borderRadius = "5px";

    if (ehJogador) {
        p.style.backgroundColor = "rgba(255, 255, 255, 0.1)";
        p.style.textAlign = "right";
        p.style.color = "#ddd";
        p.innerText = "Você: " + texto;
    } else {
        // Texto do Jogo
        p.style.backgroundColor = "transparent";
        p.style.textAlign = "left";
        p.style.color = "#fff";
        p.innerText = texto;
    }

    historico.appendChild(p);
    // Rola para baixo automaticamente
    historico.scrollTop = historico.scrollHeight;
}

// 3. Ação do botão "Iniciar Jogo" (Aquele que aparece primeiro)
const btnIniciar = document.getElementById("btn-iniciar-jogo");
if (btnIniciar) {
    btnIniciar.addEventListener("click", async () => {
        // 1. Esconde a película preta se tiver
        if (overlay) overlay.classList.add("hidden");

        // 2. Monta a tela de chat
        configurarAreaDeJogo();
        
        // 3. Chama o Java para começar o jogo
        try {
            const resp = await fetch("/api/jogo/iniciar", { method: "POST" });
            
            if (!resp.ok) throw new Error("Erro no servidor: " + resp.status);
            
            const data = await resp.json();
            
            idSessaoAtual = data.idSessao;
            adicionarAoHistorico(data.texto, false); // Mostra o texto inicial do Java
        } catch (e) {
            console.error(e);
            adicionarAoHistorico("Erro ao conectar com o servidor. Verifique se o Java está rodando sem erros.", false);
        }
    });
}

// 4. Enviar ação (Escolha do jogador)
async function enviarAcao() {
    const inputEl = document.getElementById("input-jogador");
    const texto = inputEl.value;
    if (!texto) return;

    // Mostra o que você digitou
    adicionarAoHistorico(texto, true); 
    inputEl.value = ""; // Limpa campo

    try {
        const resp = await fetch("/api/jogo/acao", {
            method: "POST",
            body: JSON.stringify({ idSessao: idSessaoAtual, input: texto })
        });
        
        const data = await resp.json();
        
        // Mostra a resposta do Java
        adicionarAoHistorico(data.texto, false); 
    } catch (e) {
        console.error(e);
        adicionarAoHistorico("Erro de conexão.", false);
    }
}

// Carregar dados iniciais (Resumo/Equipe)
async function carregarInicial() {
    try {
        const resp = await fetch("/api/inicial");
        const data = await resp.json();
        document.querySelector(".logo").textContent = data.titulo;
        document.getElementById("texto-resumo").innerText = data.resumo;
        
        const lista = document.querySelector(".team-list");
        if(lista && data.equipe) {
            lista.innerHTML = "";
            data.equipe.forEach(m => {
                const li = document.createElement("li");
                li.innerHTML = `<strong>${m.nome}</strong> – ${m.papel}`;
                lista.appendChild(li);
            });
        }
    } catch (e) { console.error("API Inicial offline", e); }
}

carregarInicial();