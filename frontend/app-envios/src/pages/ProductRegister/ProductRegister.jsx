import { useState } from "react";
import axios from "axios";

import styles from "./ProductRegister.module.css";

const ProductRegister = () => {

  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");
  const [imageUrl, setImageUrl] = useState("");

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");

    try {
      const response = await axios.post("http://localhost:3001/produtos", {
        nome: name,
        preco: parseFloat(price),
        descricao: description,
        imagem: imageUrl
      });

      console.log("Cadastro realizado com sucesso:", response.data);
      setMessage("Produto cadastrado com sucesso!");
      
      setName("");
      setPrice("");
      setDescription("");
      setImageUrl("");
    } catch (error) {
      console.error("Erro no cadastro:", error);
      setMessage("Erro ao cadastrar produto.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <div>
        <h1>Cadastrar Produto</h1>
        <form onSubmit={handleSubmit} className={styles.form}>

          <div className={styles.formGroup}>
            <label htmlFor="name">Nome do Produto</label>
            <input type="text" name="name" id="name" value={name} onChange={(e) => setName(e.target.value)} required/>
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="price">Preço</label>
            <input type="number" name="price" id="price" value={price} onChange={(e) => setPrice(e.target.value)} step="0.01" min="0" required/>
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="description">Descrição</label>
            <textarea name="description" id="description" value={description} onChange={(e) => setDescription(e.target.value)} required/>
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="imageUrl">URL da Imagem</label>
            <input type="url" name="imageUrl" id="imageUrl" value={imageUrl} onChange={(e) => setImageUrl(e.target.value)} required/>
          </div>

          <div className={styles.formActions}>
            <button type="submit" className={styles.submitButton} disabled={loading}>
              {loading ? "Cadastrando..." : "Cadastrar"}
            </button>
          </div>
          
          {message && <p>{message}</p>}
        </form>
      </div>
    </>
  );
};

export default ProductRegister;
