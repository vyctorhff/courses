import { FC } from "react";

export const ArgumentComp: FC<{ name: string, last?: string }> = (props) => {
    return (<>
        <h4>ArgumentComp</h4>
        <h3>Props comps</h3>
        <p>First props name: {props.name} and last name: {props.last}</p>
    </>);
};

export const ArgumentComp2: FC<{ person: { name: string }, size: number }> = (props) => {
    return (<>
        <h4>ArgumentComp2</h4>
        <p>Person: {props.person.name}</p>
        <span>Size: {props.size}</span>
    </>);
};


type ArgumentModel = {
    person: { name: string };
    size: number
}
// simplificação sem o return
export const ArgumentComp3: FC<ArgumentModel> = ({ person, size}) => (
    <>
        <h4>ArgumentComp3</h4>
        <p>Person: {person.name}</p>
        <span>Size: {size}</span>
    </>
);