'use client';

import { FC, useEffect, useState } from "react";

/**
 * React foi escrito supondo que vc fará componentes como funções puras
 * 
 * Função pura
 *  - Ela se mantém independente. Não altera nenhum objeto ou variável que existia antes de ser chamada.
 *  - Mesmas entradas, mesma saída. Dados os mesmos dados de entrada, uma função pura deve sempre retornar o mesmo resultado.
 * 
 * Problemas
 *  o componente alterou uma variável preexistente durante a renderização.
 *  Isso geralmente é chamado de "mutação" para soar um pouco mais assustador.
 *  Funções puras não modificam variáveis ​​fora do escopo da função nem objetos criados antes da chamada
 * 
 * Embora a programação funcional dependa muito da pureza, em algum momento, em algum lugar, algo precisa mudar.
 * 
 * Em React, os efeitos colaterais geralmente ficam dentro dos manipuladores de eventos.
 * Os manipuladores de eventos são funções que o React executa quando você realiza alguma ação.
 * Por exemplo, quando clica em um botão. Mesmo que os manipuladores de eventos sejam definidos dentro do seu componente, 
 * eles não são executados durante a renderização! Portanto, os manipuladores de eventos não precisam ser puros.
 */


/**
 * Clico de vida e hooks
 * 
 * No Next.JS precisa marcar como 'use client';
 */

export const LifeCycle: FC = () => {
    const [contador, setContador] = useState(0);

    // Qualquer atualizado do componente será interceptado
    useEffect(() => {
        console.log("Algum estado foi atualizado");
    });

    useEffect(() => {
        console.log(`Componente atualizado apenas quando contador for atualizado: ${contador}`);
    }, [contador]);

    return (<>
        <h4>File Cycle</h4>

        <fieldset style={{ width: '200px' }}>
            <legend>useState and useEffect</legend>
            <label>Contador: {contador}</label><br/>
            <button onClick={() => setContador(contador + 1)}>Alterar</button>
        </fieldset>
    </>);
};