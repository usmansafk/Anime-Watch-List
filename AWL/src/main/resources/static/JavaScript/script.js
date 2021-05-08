//-----CRUD for an anime-----
class AnimeWatchList {
  constructor(name, count, rating) {
    this.name = name;
    this.count = count;
    this.title = title;
  }
}

class CRUD {
  updateWatchList = false;
  updateTarget = null;

  //READ
  static showAnimes() {
    const anime = Anime.getAnime();
    anime.forEach((anime) => {
      CRUD.addAnimeToWatchList(anime);
    });
  }
  //CREATE
  static addAnimeToWatchList(anime) {
    const table = document.querySelector("watchlist");
    const row = document.querySelector("tr");

    row.innerHTML = `
        <td>${anime.name}</td>
        <td>${book.count}</td>
        <td>${book.rating}</td>
        <td><i class="fas fa-trash-alt text-danger" onClick="CRUD.deleteAnime(event, '${anime.name}')"></i></td>
        <td><i class="far fa-edit text-primary" onClick="CRUD.updateAnime(event, '${anime.name}')"></i></td>`;
    table.appendChild(row);
  }

  //READ>if already exists
  static checkForAnime(name) {
    const anime = Anime.getAnime();
    let alert = false;

    anime.forEach((show, id) => {
      if (show.name === name) {
        alert = true;
      }
    });
    return alert;
  }

  //UPDATE
  static updateAnimeInWatchList(anime) {
    if (CRUD.updateWatchList && CRUD.updateTarget) {
      let row = CRUD.updateTarget.parentElement.parentElement;
      row.innerHTML = `
          <td>${anime.name}</td>
          <td>${anime.count}</td>
          <td>${anime.rating}</td>
          <td><i class="fas fa-trash-alt text-danger" onClick="CRUD.deleteAnime(event, '${anime.name}')"></i></td>
          <td><i class="far fa-edit text-primary" onClick="CRUD.updateAnime(event, '${anime.name}')"></i></td>`;
    }
  }

  // () for UPDATING and DELETING
  static updateAnime(event, name) {
    CRUD.updateTarget = event.target;
    CRUD.updateWatchList = true;

    let animeList = Anime.getAnime();
    animeList.forEach((anime, id) => {
      if (anime === name) {
        document.querySelector("#name").value = anime.name;
        document.querySelector("#count").value = anime.count;
        document.querySelector("#rating").value = anime.rating;
      }
    });

    document.getElementById("submitBtn").innerHTML = "UPDATE ANIME";
  }

  static removeAnime(event, name) {
    event.target.parentElement.parentElement.remove();
    Store.removeAnime(name);
    CRUD.showAlertMessage("Anime removed from watch list", "success");
  }

  static refreshInputBox() {
    document.querySelector("#name").value = "";
    document.querySelector("#count").value = "";
    document.querySelector("#rating").value = "";
    CRUD.updateWatchList = false;
    CRUD.updateTarget = null;
    document.querySelector("#name").disabled = false;
    document.getElementById("create-button").innerHTML = "ADD TO MY WATCHLIST";
  }

  static confirmCRUD(message, classname) {
    const div = document.createElement("div");
    div.className = `alert alert-${classname}`;
    div.innerHTML = message;
    const container = document.querySelector(".container");
    container.insertBefore(div, document.querySelector("#anime-form"));

    setTimeout(() => {
      document.querySelector(".alert").remove();
    }, 2000);
  }
}
