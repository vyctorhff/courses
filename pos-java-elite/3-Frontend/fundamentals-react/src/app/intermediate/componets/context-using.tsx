import { FC, useContext } from "react";
import { ColorContext, RollTheDice } from "./context";

export const UsingContext:FC = () => {
    const colorName = useContext(ColorContext);

    return (<>
        <span>Conntext value is: {colorName}</span>
    </>);
};

export const UpdateContext:FC<{ update: ()=> void}> = (update) => {
    const colorName = useContext(ColorContext);
    console.log(`In update component: ${colorName}`);

    return (<>
        <span onClick={() => update.update()}>Click to Update context</span>
    </>);
};

export const DiceContext:FC = () => {
    const {value, callBack} = useContext(RollTheDice);
    return (<>
        <span>Context dice value: {value}</span>
    </>)
};

export const UpdateDiceContext:FC = () => {
    const {value, callBack} = useContext(RollTheDice);
    return (<>
        <span onClick={() => callBack(value + 1)}>Click on me to change!</span>
    </>)
};