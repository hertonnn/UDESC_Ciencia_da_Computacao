class NFAWithEpsilon:
    def __init__(self, states, alphabet, transitions, start_state, accept_states):
        self.states = states  # Conjunto de estados (Q)
        self.alphabet = alphabet  # Alfabeto (Σ)
        self.transitions = transitions  # Função de transição (δ), incluindo epsilon
        self.start_state = start_state  # Estado inicial (q0)
        self.accept_states = accept_states  # Estados de aceitação (F)

    def delta(self, state, symbol):
        """Função de transição delta que lida com transições de símbolos e epsilon."""
        if (state, symbol) in self.transitions:
            return self.transitions[(state, symbol)]
        return set()

    def epsilon_closure(self, states):
        """Calcula o fecho epsilon (conjunto de todos os estados alcançáveis através de transições epsilon)."""
        closure = set(states)  # Inicia com os estados atuais
        stack = list(states)   # Usa uma pilha para processar os estados

        while stack:
            current_state = stack.pop()
            # Se há uma transição epsilon a partir do estado atual
            if (current_state, 'ε') in self.transitions:
                for next_state in self.transitions[(current_state, 'ε')]:
                    if next_state not in closure:
                        closure.add(next_state)
                        stack.append(next_state)
        return closure

    def accepts(self, input_string):
        """Função que verifica se o NFA com epsilon aceita uma string de entrada."""
        current_states = self.epsilon_closure({self.start_state})  # Fecho epsilon do estado inicial

        for symbol in input_string:
            next_states = set()
            for state in current_states:
                next_states.update(self.delta(state, symbol))
            current_states = self.epsilon_closure(next_states)  # Fecho epsilon dos novos estados

        # Verifica se algum estado atual é um estado de aceitação
        return bool(current_states.intersection(self.accept_states))

# Exemplo de uso:
states = {'q0', 'q1', 'q2', 'q3'}
alphabet = {'a', 'b'}
transitions = {
    ('q0', 'ε'): {'q1'},       # Transição epsilon de q0 para q1
    ('q1', 'a'): {'q1', 'q2'}, # Transição não determinística com 'a'
    ('q2', 'b'): {'q3'},       # Transição com 'b'
    ('q1', 'ε'): {'q3'}        # Outra transição epsilon de q1 para q3
}
start_state = 'q0'
accept_states = {'q3'}

nfa = NFAWithEpsilon(states, alphabet, transitions, start_state, accept_states)

# Testa se o NFA aceita a string 'ab'
print(nfa.accepts('ab'))  # True
