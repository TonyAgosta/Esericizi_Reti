Si scriva un programma JAVA che riceve in input un filepath che individua una directory D, stampa le informazioni del contenuto di quella directory e, ricorsivamente,
tutti i file contenuti nelle sottodirectory di D.
Il programma deve essere strutturato come segue:
attiva un thread produttore ed un insieme di k thread consumatori:
Il produttore comunica con i consumatori mediante una coda,visita ricorsivamente la directory data ed, eventualmente tutte le sottodirectory e mette nella coda il nome di ogni 
directory individuata.
I consumatori prelevano dalla coda i nomi delle directories e stampano il loro contenuto.
La coda deve essere realizzata con una LinkedList.
