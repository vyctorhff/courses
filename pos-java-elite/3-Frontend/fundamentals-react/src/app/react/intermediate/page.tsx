'use client';

import { useState } from "react";
import { Children1 } from "./componets/children-pass";
import { ColorContext, RollTheDice } from "./componets/context";
import { DiceContext, UpdateContext, UpdateDiceContext, UsingContext } from "./componets/context-using";
import { LifeCycle } from "./componets/life-cycle-and-hooks";
import { State1 } from "./componets/state-comp";
import { FetchServer } from "./componets/fetch-server";
import { FetchClient } from "./componets/fetch-client";
import Link from "next/link";

export default function Intermediate() {
    const [color, setColor] = useState('red');
    const [diceValue, rollDice] = useState(1);

    return (<>
        <h1>Intermediate</h1>
        <Link href="/">Back</Link>
        <hr/>

        <h2>State</h2>
        <State1/>
        <hr/>

        <h2>Chlidren</h2>
        <Children1 text="testing">
            <p>Some code in html</p>
        </Children1>
        <hr/>

        <h2>Life Cicly</h2>
        <LifeCycle/>
        <hr/>

        <h2>Context</h2>
        <ColorContext.Provider value={color}>
            <UsingContext/>
            <br/>
            <UpdateContext update={() => setColor('white')}/>
        </ColorContext.Provider>

        <br/>
        <RollTheDice.Provider value={{value: diceValue, callBack: rollDice}}>
            <DiceContext/>
            <br/>
            <UpdateDiceContext/>
        </RollTheDice.Provider>
        <hr/>

        <h2>Fetch</h2>
        <FetchClient/><br/>
        <FetchServer/><br/>
        <hr/>
    </>);
}