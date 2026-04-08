import Link from "next/link";

export default function Home() {
  return (<>
    <h1>Fundametals</h1>

    <h3><Link href="/react/basic">Basic</Link></h3>
    <h3><Link href="/react/intermediate">Intermediate</Link></h3>
    <h3><Link href="/react/itermediate/routes/victor">Intermediate: Routes - victor</Link></h3>
    <h3><Link href="/react/itermediate/routes/name-info/item-info">Intermediate: Routes - name-info and item</Link></h3>
    <h3><Link href="/react/advance">Advance</Link></h3>
  </>);
}
