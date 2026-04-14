'use client';
import { FC, useEffect, useRef, useState } from "react";

/**
 * Hooks são funções que permitem:
 *  - gerenciar estados
 *      - useState
 *      - useContext
 *  - gerencia o ciclo de vida do componente
 *      - useEffect
 *      - 
 *  - criar lógica reutilizável
 * 
 * Não pode colocar hooks dentro de if, pois a mesma quantidade de hooks
 * de uma renderização para outra no react deve ter a mesma quantidade de hooks
 */

export const HookEffect:FC = () => {
    const [value, setValue] = useState(true);

    useEffect(() => {
        console.log('Executa no criação do componente');
    }, []);

    useEffect(() => {
        console.log('Executa na alteração do componente');
    }, [value]);

    useEffect(() => {
        return () => {
            // testar na navegacao Back ou quando é removido da visualização
            console.log('Antes da destruicao'); 
            console.log('Executa na destruicao');
        };
    }, []);

    return (<>
        <span>Effect</span>
    </>);
};


// ##################################################################################
/**
 * Pode contar operações, instruções ou varáveis que ficariam dentro do componente.
 * Permitem reutizalição e melhor manutenção.
 * 
 * É uma boa práticar criar um pasta /hooks e colocar o código lá.
 * É boa prática nomear 'use' no início 
 */
export const useContator = (): [number, ()=>void, ()=>void] => {
    const [contator, setContator] = useState(0);

    const increment = () => setContator(contator + 1);
    const decrement = () => setContator(contator - 1);

    //return {contator,increment,decrement};
    // ou
    return [contator, increment, decrement];// é bom definir o retorno da funçaõ neste caso
};

export const HookCustom:FC = () => {
    const [contador, increment, decrement] = useContator();
    return (<>
        <h4>Custom Hook</h4>
        <span>contador: {contador}</span>
        <button onClick={increment}>Increment</button>
        <button onClick={decrement}>Decrement</button>
    </>);
};

// ##################################################################################
// ref
/**
 * É utilizando para controlar o estado de variável que não precisar ser atualizando quando alterada.
 * É o oposto do useState que a alteração força a atualização do componente.
 * 
 * Em outras palavras, o useRef é utilizando quando vc quer guardar um valor que não seja alterado
 * mesmo que o componente seja atualizado por outras ações
 * 
 * Atenção: não ler o valor da current durante o rendering, apenas em funções
 * 
 * Dá para usar para guardar a penultima alteração feita em um estado
 */
export const HookRef:FC = () => {
    const count = useRef(0);
    const [ss, setSS] = useState(false);

    const inputRef = useRef(null);

    return (<>
        <span>Count: {count.current}</span>
        <button onClick={() => {count.current = count.current + 1}}>Update count</button>
        <button onClick={() => {setSS(true)}}>Force update state</button>

        <br/>
        <span>For input</span>
        <input ref={inputRef} onChange={(e) => setSS(true)}/>
    </>);
};


// ##################################################################################
// useMen + useCallback
/**
 * Usado quando há métodos ou chamada que gastam muito recurso de memória ou processamento.
 * Neste casos, é usado para evitar repetição de chamadas
 */