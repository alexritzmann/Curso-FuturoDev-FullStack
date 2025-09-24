import { useState, useEffect } from 'react';
import axios from 'axios';
import {
  Box,
  Grid,
  Typography,
  Card,
  CardContent,
  CardMedia,
  Button,
  IconButton,
  Modal,
  Chip,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions
} from '@mui/material';
import { Edit, Delete, Close } from '@mui/icons-material';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [openModal, setOpenModal] = useState(false);
  const [deleteDialog, setDeleteDialog] = useState(false);
  const [productToDelete, setProductToDelete] = useState(null);

  const fetchProducts = async () => {
    try {
      setLoading(true);
      const response = await axios.get('http://localhost:3001/produtos');
      setProducts(response.data);
    } catch (err) {
      setError('Erro ao carregar produtos');
      console.error('Erro:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  const handleOpenModal = (product) => {
    setSelectedProduct(product);
    setOpenModal(true);
  };

  const handleCloseModal = () => {
    setOpenModal(false);
    setSelectedProduct(null);
  };

  const handleOpenDeleteDialog = (product) => {
    setProductToDelete(product);
    setDeleteDialog(true);
  };

  const handleCloseDeleteDialog = () => {
    setDeleteDialog(false);
    setProductToDelete(null);
  };

  const handleDelete = async () => {
    console.log('Excluindo produto:', productToDelete);
    handleCloseDeleteDialog();
  };

  const handleEdit = (product) => {
    console.log('Editando produto:', product);
  };

  const truncateDescription = (description, maxLength = 150) => {
    if (description.length <= maxLength) return description;
    return description.substring(0, maxLength) + '...';
  };

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '50vh' }}>
        <Typography variant="h6">Carregando produtos...</Typography>
      </Box>
    );
  }

  if (error) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '50vh' }}>
        <Typography variant="h6" color="error">{error}</Typography>
      </Box>
    );
  }

  return (
    <Box sx={{ padding: 3 }}>
      <Typography variant="h4" component="h1" gutterBottom sx={{ 
        textAlign: 'center', 
        fontWeight: 'bold',
        mb: 4 
      }}>
        Lista de Produtos
      </Typography>

      {products.length === 0 ? (
        <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '50vh' }}>
          <Typography variant="h6">Nenhum produto cadastrado</Typography>
        </Box>
      ) : (
        <Grid 
          container 
          spacing={3} 
          justifyContent="center"
          sx={{ width: '100%', margin: 0 }}
        >
          {products.map((product) => (
            <Grid 
              item 
              xs={12} 
              sm={6} 
              md={4} 
              lg={3}
              key={product.id}
              sx={{
                display: 'flex',
                justifyContent: 'center'
              }}
            >
              <ProductCard 
                product={product}
                onViewMore={handleOpenModal}
                onEdit={handleEdit}
                onDelete={handleOpenDeleteDialog}
                truncateDescription={truncateDescription}
              />
            </Grid>
          ))}
        </Grid>
      )}

      <ProductModal 
        open={openModal}
        product={selectedProduct}
        onClose={handleCloseModal}
      />

      <DeleteDialog 
        open={deleteDialog}
        product={productToDelete}
        onClose={handleCloseDeleteDialog}
        onConfirm={handleDelete}
      />
    </Box>
  );
};

const ProductCard = ({ product, onViewMore, onEdit, onDelete, truncateDescription }) => {
  return (
    <Card sx={{ 
      display: 'flex',
      flexDirection: 'column',
      width: '100%',
      maxWidth: 320,
      height: 400,
      minHeight: 400,
      transition: 'transform 0.2s, box-shadow 0.2s',
      '&:hover': {
        transform: 'translateY(-4px)',
        boxShadow: 4
      }
    }}>
      <CardMedia
        component="img"
        sx={{ 
          width: '100%',
          height: 180,
          objectFit: 'contain',
          flexShrink: 0
          
        }}
        onClick={() => onViewMore(product)}
        image={product.imagem}
        alt={product.nome}
        onError={(e) => {
          e.target.src = 'https://via.placeholder.com/320x180/cccccc/969696?text=Imagem+Não+Disponível';
        }}
      />
      
      <CardContent sx={{ 
        flex: 1,
        display: 'flex',
        flexDirection: 'column',
        padding: 2,
        overflow: 'hidden'
      }}>
        <Typography 
          variant="h6" 
          component="h2" 
          gutterBottom
          sx={{ 
            fontWeight: 'bold',
            fontSize: '1.1rem',
            lineHeight: 1.2,
            minHeight: '2.4em',
            display: '-webkit-box',
            WebkitLineClamp: 2,
            WebkitBoxOrient: 'vertical',
            overflow: 'hidden'
          }}
        >
          {product.nome}
        </Typography>
        
        <Box sx={{ 
          flex: 1,
          minHeight: 80,
          overflow: 'hidden',
          mb: 1
        }}>
          <Typography 
            variant="body2" 
            color="text.secondary"
            sx={{ 
              lineHeight: 1.4,
              display: '-webkit-box',
              WebkitLineClamp: 3,
              WebkitBoxOrient: 'vertical',
              overflow: 'hidden'
            }}
          >
            {truncateDescription(product.descricao)}
          </Typography>
          
          {product.descricao.length > 150 && (
            <Button 
              size="small" 
              onClick={() => onViewMore(product)}
              sx={{ 
                textTransform: 'none',
                fontWeight: 'bold',
                p: 0,
                mt: 0.5
              }}
            >
              Veja mais
            </Button>
          )}
        </Box>
        
        <Box sx={{ 
          display: 'flex', 
          justifyContent: 'space-between',
          alignItems: 'center',
          mt: 'auto',
          flexShrink: 0
        }}>
          <Typography 
            variant="h6" 
            color="primary"
            sx={{ 
              fontWeight: 'bold',
              fontSize: '1.1rem'
            }}
          >
            R$ {parseFloat(product.preco).toLocaleString('pt-BR', {
              minimumFractionDigits: 2,
              maximumFractionDigits: 2
            })}
          </Typography>
          
          <Box sx={{ display: 'flex', gap: 1 }}>
            <IconButton 
              size="small" 
              onClick={() => onEdit(product)}
              sx={{ 
                backgroundColor: 'primary.main',
                color: 'white',
                '&:hover': {
                  backgroundColor: 'primary.dark'
                }
              }}
            >
              <Edit fontSize="small" />
            </IconButton>
            
            <IconButton 
              size="small" 
              onClick={() => onDelete(product)}
              sx={{ 
                backgroundColor: 'error.main',
                color: 'white',
                '&:hover': {
                  backgroundColor: 'error.dark'
                }
              }}
            >
              <Delete fontSize="small" />
            </IconButton>
          </Box>
        </Box>
      </CardContent>
    </Card>
  );
};

const ProductModal = ({ open, product, onClose }) => {
  if (!product) return null;

  return (
    <Modal
      open={open}
      onClose={onClose}
      aria-labelledby="product-modal-title"
      aria-describedby="product-modal-description"
    >
      <Box sx={{
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '90%',
        maxWidth: 600,
        maxHeight: '90vh',
        bgcolor: 'background.paper',
        boxShadow: 24,
        borderRadius: 2,
        overflow: 'auto'
      }}>
        <Box sx={{ display: 'flex', justifyContent: 'flex-end', p: 1 }}>
          <IconButton onClick={onClose}>
            <Close />
          </IconButton>
        </Box>
        
        <Box sx={{ display: 'flex', flexDirection: { xs: 'column', md: 'row' }, p: 3 }}>
          <CardMedia
            component="img"
            sx={{ 
              width: { xs: '100%', md: '40%' },
              height: 200,
              objectFit: 'cover',
              borderRadius: 1,
              mb: { xs: 2, md: 0 }
            }}
            image={product.imagem}
            alt={product.nome}
          />
          
          <Box sx={{ ml: { xs: 0, md: 3 }, flex: 1 }}>
            <Typography id="product-modal-title" variant="h5" component="h2" gutterBottom>
              {product.nome}
            </Typography>
            
            <Chip 
              label={`R$ ${parseFloat(product.preco).toLocaleString('pt-BR', {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2
              })}`}
              color="primary"
              variant="filled"
              sx={{ mb: 2, fontWeight: 'bold' }}
            />
            
            <Typography id="product-modal-description" variant="body1" sx={{ mt: 2 }}>
              {product.descricao}
            </Typography>
            
            <Typography variant="caption" color="text.secondary" sx={{ mt: 2, display: 'block' }}>
              ID: {product.id}
            </Typography>
          </Box>
        </Box>
      </Box>
    </Modal>
  );
};

const DeleteDialog = ({ open, product, onClose, onConfirm }) => {
  if (!product) return null;

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Confirmar Exclusão</DialogTitle>
      <DialogContent>
        <Typography>
          Tem certeza que deseja excluir o produto <strong>"{product.nome}"</strong>?
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ mt: 1 }}>
          Esta ação não pode ser desfeita.
        </Typography>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Cancelar</Button>
        <Button 
          onClick={onConfirm} 
          color="error"
          variant="contained"
        >
          Excluir
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ProductList;