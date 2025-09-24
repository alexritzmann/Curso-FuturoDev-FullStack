import { useState } from "react";
import axios from "axios";

import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Alert from '@mui/material/Alert';

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
      // Limpa o formulário
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
    <Box // Usando Box do MUI como container principal
      component="main"
      sx={{
        maxWidth: 800,
        mx: 'auto', // Centraliza horizontalmente
        p: 3, // Padding
      }}
    >
      <Typography 
        component="h1" 
        variant="h4" 
        gutterBottom // Adiciona margem inferior
        sx={{ 
          textAlign: 'center', 
          fontWeight: 'bold',
          mb: 4 // Margem inferior maior
        }}
      >
        Cadastrar Produto
      </Typography>

      <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{
          display: 'flex',
          flexDirection: 'column',
          gap: 3, // Cria um espaçamento consistente entre os campos
        }}
      >
        {/* Campo Nome do Produto */}
        <TextField
          fullWidth
          variant="outlined"
          label="Nome do Produto"
          id="name"
          name="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />

        {/* Campo Preço */}
        <TextField
          fullWidth
          variant="outlined"
          label="Preço"
          type="number"
          id="price"
          name="price"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          inputProps={{ 
            step: "0.01", 
            min: "0" 
          }}
          required
        />

        {/* Campo Descrição - Multiline */}
        <TextField
          fullWidth
          variant="outlined"
          label="Descrição"
          id="description"
          name="description"
          multiline // Transforma em textarea
          minRows={4} // Número mínimo de linhas visíveis :cite[1]:cite[7]
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />

        {/* Campo URL da Imagem */}
        <TextField
          fullWidth
          variant="outlined"
          label="URL da Imagem"
          type="url"
          id="imageUrl"
          name="imageUrl"
          value={imageUrl}
          onChange={(e) => setImageUrl(e.target.value)}
          required
        />

        {/* Área de Ações e Feedback */}
        <Box sx={{ mt: 2 }}> {/* Margem no topo */}
          <Button
            type="submit"
            variant="contained" // Estilo preenchido
            size="large" // Botão grande
            disabled={loading}
            fullWidth
          >
            {loading ? "Cadastrando..." : "Cadastrar Produto"}
          </Button>
        </Box>

        {message && (
          <Alert 
            severity={message.includes("Erro") ? "error" : "success"} 
            sx={{ mt: 2 }}
          >
            {message}
          </Alert>
        )}
      </Box>
    </Box>
  );
};

export default ProductRegister;
