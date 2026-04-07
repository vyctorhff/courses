import Link from "next/link";
import { ArgumentComp, ArgumentComp2, ArgumentComp3 } from "./components/arguments-comp";
import { Condicional, Condicional2, Condicional3 } from "./components/condicional-comp";
import { Event1 } from "./components/events-comp";
import { Iterator2, IteratorSimple } from "./components/for-comp";
import { HelloComp } from "./components/hello-comp";

export default function Basic() {

  const callBack = (item:number) => console.log(`In Basic comp: ${2 * item}`);

  return (<>
    <h1>Fundametals</h1>
    <Link href="/">Back</Link>
    <hr/>
    
    <h2>Components</h2>
    <HelloComp/>
    <ArgumentComp name="fulano"/>
    <ArgumentComp name="fulano" last="de tal"/>
    <ArgumentComp2 person={{ name: "fulano"}} size={20}/>
    <ArgumentComp3 person={{ name: "fulano"}} size={20} list={['bbb', 'ccc']} funCallBack={callBack}/>
    <hr/>

    <h2>Condicional</h2>
    <Condicional name="bbb" cond={false}/>
    <Condicional2 cond={true}/>
    <Condicional3 cond={true}/>
    <hr/>

    <h2>Iterator</h2>
    <IteratorSimple/>
    <Iterator2/>
    <hr/>

    <h2>Events</h2>
    <Event1/>
    <hr/>
  </>);
}
