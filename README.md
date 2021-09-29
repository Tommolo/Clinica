# Clinica
Per questo progetto ho scelto 4 casi d'uso:

Caso d'uso UC1: consulta offerta
Attore primario: utente non registrato
Scenario principale di successo: 
  -L'utente consulta l'elenco delle tipologie di esame
  -L'utente sceglie una tipologia di esame e ne richiede i dettagli 
  -Il sistema mostra descrizione e nome della tipologia di esame
L'utente ripete i passi precedenti un numero indefinito di volte


Caso d'uso UC2: crea esame (prenotazione)
Attore primario: amministrazione
Scenario principale di successo: 
  -L'ammistrazione crea un esame
  -L'amministrazione imposta una tipologia di esame all'esame creato
  -L'amministrazione associa un paziente all'esame creato
  -Il sistema registra l'esame (impostando automaticamente la data di prenotazione)
Precondizioni: l'amministratore è identificato e autenticato

Caso d'uso UC3: consulta risultati proprio esame
Attore primario: paziente
Scenario principale:
  -Il paziente consulta l'elenco dei propri esami
  -Il sistema mostra al paziente l'elenco dei suoi esami
  -Il paziente chiede il dettaglio di un esame
  -Il sistema mostra il dettaglio dell'esame
  -Il paziente ripete i passi precedenti finché necessario
Precondizioni: il paziente è identificato e autenticato

Caso d'uso UC4: inserimento tipologia di esame
Attore primario: amministrazione
Scenario principale:
-L'amministratore inserisce una nuova tipologia di esame specificandone i dettagli
-Il sistema registra la tipologia di esame
-I punti precedenti vengono ripetuti fino a che necessario
Precondizioni: l'amministratore è identificato e autenticato

Caso d'uso UC5: inserimento medico. Attore primario: amministrazione. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato come quello dell’esame.
Scenario principale di successo: L'amministrazione seleziona la voce "inserisci medico" Il sistema mostra la form L'amministrazione inserisce nome, cognome, specializzazione e foto del medico Il sistema registra il medico e mostra la lista di tutti i medici
