# TP3 - Réponses aux exercices


* Question 1(Milestone)<br>
  ![img.png](./images/tp3/img.png) <br><br>

* Question 2(Issues) <br>
  ![img_1.png](images/tp3/img_1.png) <br>
  ![img_2.png](images/tp3/img_2.png) <br>
  ![img_3.png](images/tp3/img_3.png) <br>
  ![img_4.png](images/tp3/img_4.png) <br>
  ![img_5.png](images/tp3/img_5.png) <br>
  ![img_6.png](images/tp3/img_6.png) <br>
  ![img_7.png](images/tp3/img_7.png) <br>
  ![img_8.png](images/tp3/img_8.png) <br>
  ![img_9.png](images/tp3/img_9.png) <br>
  ![img_10.png](images/tp3/img_10.png) <br>
  ![img_11.png](images/tp3/img_11.png) <br>
  ![img_21.png](images/tp3/img_21.png) <br><br>

* Question 3(PRs) <br>
  ![img_12.png](./images/tp3/img_12.png) <br>
  ![img_13.png](./images/tp3/img_13.png) <br>
  ![img_14.png](./images/tp3/img_14.png) <br>
  ![img_15.png](./images/tp3/img_15.png) <br>
  ![img_16.png](./images/tp3/img_16.png) <br>
  ![img_17.png](./images/tp3/img_17.png) <br>
  ![img_18.png](./images/tp3/img_18.png) <br>
  ![img_19.png](./images/tp3/img_19.png) <br>
  ![img_22.png](./images/tp3/img_22.png) <br><br>

* Question 4 (Github Project) <br>
  ![img_20.png](./images/tp3/img_20.png) <br><br>


* Capture d'écran du pipeline Heroku <br>
  ![img_23.png](./images/tp3/img_23.png)

* User stories <br>
  * 1 .	En tant que vendeur, je souhaite être certain que l’argent me sera acheminé proprement afin de pouvoir garantir mes gains, c’est-à-dire que l’utilisateur possède réellement l’argent dans un compte. <br> <br>
  Lors de la création d’un compte acheteur, l’utilisateur devra mettre obligatoirement un dépôt d’un certain montant. <br><br>
  **Condition de succès** <br><br>
  1.	Le montant que l’utilisateur entre dans le système ne peut pas être négatif
  2.	L’utilisateur doit avoir un compte de banque valide. <br><br>

  * 2 .	En tant qu’acheteur, je veux pouvoir visualiser les produits selon certaines catégories afin de pouvoir faire des recherches plus spécifiques. <br> <br>
  Lors de la recherche d’un article, l’utilisateur pourra entrer une certaine catégorie et celle-ci retournera les articles en lien avec celle-ci. <br><br>
  **Condition de succès** <br><br>
  1.	Lorsque le vendeur ajoute son article, il devra sélectionner une catégorie pour celui-ci selon des catégories prédéfinies
  2.	Si l’utilisateur ne rentre aucune catégorie dans sa recherche, le système ne fait que retourner la liste de tous les articles
  3.	L’acheteur peut entrer une catégorie pour la recherche d’article et celle-ci retournera que les articles en lien avec la catégorie <br><br>
  
  
  * 3 .	En tant qu’acheteur, je veux m’assurer que je peux visualiser le prix d’un article selon ma devise. <br> <br>
  Lors de la recherche d’un article, l’utilisateur pourra entrer sa devise et le prix des articles seront dynamique selon la devise de l’utilisateur. <br><br>
  **Condition de succès** <br><br>
  1.	La devise par défaut du système est le dollar canadien
  2.	Les devises supportées sont le USD et EURO
  3.	Le système fait la conversion de devise selon l’utilisateur <br><br>

  * 4 .	En tant que vendeur, je souhaite avoir la possibilité de lister les produits que je veux vendre par catégorie. <br> <br>
  Lors de la création d’un produit, le vendeur doit l’assigner a une catégorie spécifique. <br><br>
  **Condition de succès** <br><br>
  1.	Le vendeur peut visualiser ces produits qu’il veut vendre dans une liste spécifique a lui
  2.	Le produit que le vendeur vient de créer, possède un champ qui s’appelle catégorie <br><br>

  * 5 .	En tant que vendeur, je souhaite avoir la possibilité de supprimer un produit de ma liste de produits que je veux vendre. <br> <br>
  Le vendeur a la possibilité de supprimer un produit. <br><br>
  **Condition de succès** <br><br>
  1.	Le vendeur visualise ces produits qu’il veut vendre dans une liste spécifique à lui
  2.	Le vendeur sélectionne le produit en question
  3.	Le vendeur supprime le produit
  4.	La liste se met à jour <br><br>

  * 6 .	En tant que vendeur, je souhaite avoir la possibilité de modifier les caractéristiques d’un produit qui se trouve dans ma liste de produit que je veux vendre. <br> <br>
  Le vendeur peut modifier les caractéristiques d’article. <br><br>
  **Condition de succès** <br><br>
  1.	Le vendeur sélectionne l’article qu’il veut modifier
  2.	Le vendeur apporte les modifications nécessaires et enregistre ces modifications
  3.	L’article est mis à jour dans la liste <br><br>

  * 7 .	En tant qu’acheteur, je souhaite avoir la possibilité de visualiser ma liste d’articles favoris que j’aurais au préalable ajouté. <br> <br>
  Le vendeur peut visualiser sa liste de favoris. <br><br>
  **Condition de succès** <br><br>
  1.	Par défaut, la liste de favoris est vide
  2.	L’acheteur sélectionne l’article qu’il veut ajouter aux favoris
  3.	L’article est ajouté à la liste <br><br>

  * 8 .	En tant qu’utilisateur, je souhaite avoir la possibilité d’être à la fois vendeur et acheteur. <br> <br>
  L’utilisateur peut avoir un même compte pour switcher entre les deux. <br><br>
  **Condition de succès** <br><br>
  1.	L’utilisateur sélectionne le type de compte qu’il veut utiliser
  2.	Les informations sont mises à jour selon le type de compte
