import { FC } from "react";

const people = [
  'Creola Katherine Johnson: mathematician',
  'Mario José Molina-Pasquel Henríquez: chemist',
  'Mohammad Abdus Salam: physicist',
  'Percy Lavon Julian: chemist',
  'Subrahmanyan Chandrasekhar: astrophysicist'
];

export const IteratorSimple: FC<{}> = (props) => {
    const list = people.map((item, index) => <li key={`people1-${index}`}>{item}</li>)
    return <ul>{list}</ul>;
};

export const Iterator2: FC<{}> = (props) => (
    /*
        Usar a key em uma iteração para indicar aos virtual dom qual é o item
        que está sendo criado.

        Evite usar apenas um index, usar keys(ids) únicos. Neste caso, 
        vamos concater um texto
    */
    <>
        <ul>
            {people.map((item, index) => <li key={`people-${index}`}>{item}</li>)}
        </ul>
    </>
);